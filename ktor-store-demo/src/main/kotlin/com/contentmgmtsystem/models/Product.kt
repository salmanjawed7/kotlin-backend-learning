package com.contentmgmtsystem.models

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val upc: Int,
    val name: String,
    val description: String
)

val IN_MEMORY_PRODUCTS = listOf(
    Product(1, "Product 1", "Description of Product 1"),
    Product(2, "Product 2", "Description of Product 2"),
    Product(3, "Product 3", "Description of Product 3")
)