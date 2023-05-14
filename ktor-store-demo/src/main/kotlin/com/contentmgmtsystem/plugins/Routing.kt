package com.contentmgmtsystem.plugins

import com.contentmgmtsystem.models.IN_MEMORY_PRODUCTS
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {

        get("/") {
            call.respondText("Hello Ktor!!!!")
        }

        get("/products") {
            call.respond(IN_MEMORY_PRODUCTS)
        }
    }
}
