package com.contentmgmtsystem.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configureContentNegotation() {
    install(ContentNegotiation) {
        json()
    }
}