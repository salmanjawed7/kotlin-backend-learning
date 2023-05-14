package com.contentmgmtsystem.plugins

import com.contentmgmtsystem.models.IN_MEMORY_PRODUCTS
import com.contentmgmtsystem.models.Product
import com.contentmgmtsystem.persistence.ProductDAO
import com.contentmgmtsystem.persistence.ProductDatabase
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*

fun Application.configureRouting() {
    routing {

        get("/") {
            call.respondText("Hello Ktor!!!!")
        }

        get("/products") {
            call.respond(ProductDatabase.dao.products())
        }

        authenticate {
            post("/add-product") {
                val body = call.receive<Product>()
                when (val insertedProduct = ProductDatabase.dao.addProduct(body)) {
                    null -> call.respondText(
                        "Failed to add product: $body",
                        status = HttpStatusCode.InternalServerError
                    )

                    else -> call.respondText("Added Product: $body", status = HttpStatusCode.Created)
                }
            }
        }

        get("/product") {
            val upc = call.request.queryParameters["upc"]
            upc?.let { safeUpc ->
                safeUpc.toIntOrNull()?.let { upcInt ->
                    when (val resultProduct = ProductDatabase.dao.productByUpc(upcInt)) {
                        null -> call.respondText("No product found for upc: $upc", status = HttpStatusCode.OK)
                        else -> call.respond(resultProduct)
                    }
                } ?: run {
                    call.respondText("Invalid UPC", status = HttpStatusCode.BadRequest)
                }
            } ?: run {
                call.respondText("Missing UPC", status = HttpStatusCode.NotFound)
            }
        }
    }
}
