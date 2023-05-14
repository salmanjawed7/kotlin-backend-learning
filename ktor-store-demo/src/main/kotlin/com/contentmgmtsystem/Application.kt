package com.contentmgmtsystem

import com.contentmgmtsystem.plugins.configureAuthentication
import com.contentmgmtsystem.plugins.configureCallLogging
import com.contentmgmtsystem.plugins.configureContentNegotation
import com.contentmgmtsystem.plugins.configureRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module, watchPaths = listOf("classes"))
        .start(wait = true)
}

fun Application.module() {
    configureAuthentication()
    configureRouting()
    configureCallLogging()
    configureContentNegotation()
}
