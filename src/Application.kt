package com.example

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.content.*
import io.ktor.http.content.*
import io.ktor.features.*
import io.ktor.gson.*
import java.io.File

fun main(args: Array<String>): Unit = io.ktor.server.jetty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson {
        }
    }

    routing {
        get("/") {
            call.respondFile(File("index.html"))
        }

        post("/sasi") {
            val s = this.context.parameters["sasi"]
            if (s == null) {
                call.respondText { "ТЫ ХУЙ" }
            } else {
                call.respondText {s}
            }
        }

    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()

