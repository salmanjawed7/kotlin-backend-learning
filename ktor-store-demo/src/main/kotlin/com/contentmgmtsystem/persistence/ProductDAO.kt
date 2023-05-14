package com.contentmgmtsystem.persistence

import com.contentmgmtsystem.models.Product
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class ProductDAO {

    suspend fun productByUpc(upc: Int): Product? {
        return query {
            ProductTable.select { ProductTable.upc.eq(upc) }.firstOrNull()?.toProduct()
        }
    }

    suspend fun products(): List<Product> {
        return query {
            ProductTable.selectAll().map { it.toProduct() }
        }
    }

    suspend fun addProduct(product: Product): Product? {
        return query {
            val insertedProduct = ProductTable.insert {
                it[upc] = product.upc
                it[name] = product.name
                it[description] = product.description
            }
            insertedProduct.resultedValues?.singleOrNull()?.toProduct()
        }
    }

    private fun ResultRow.toProduct(): Product {
        return Product(
            upc = this[ProductTable.upc],
            name = this[ProductTable.name],
            description = this[ProductTable.description]
        )
    }

    private suspend fun <T> query(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}