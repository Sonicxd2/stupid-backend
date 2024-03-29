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
import java.util.concurrent.ThreadLocalRandom

fun main(args: Array<String>): Unit = io.ktor.server.jetty.EngineMain.main(args)

data class Hueta(val a: String)


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
                call.respond(Hueta(s))
            }
        }

        var shahlikState = 0
        get("/prepairshashlik") {
            shahlikState += 10
            if (shahlikState > 100) {
                call.respondText { "ТЫ ЛОХ ШАШЛЫК СГОРЕЛ" }
                shahlikState = 0
            } else {
                call.respondText { "ШАШЛЫК ГОТОВ НА $shahlikState%" }
            }
        }

        get("/pokuritbkalik") {
            call.respondText { ThreadLocalRandom.current().nextBoolean().toString() }
//            if (ThreadLocalRandom.current().nextBoolean()) {
//                call.respondText { "ПРИШЕЛ ТИМЛИДЫЧ И ВЫКУРИЛ ВЕСЬ КАЛИК!" }
//            } else {
//                call.respondText { "ВЫ ПОКУРИЛИ КАЛИК И ЗАРАЗИЛИСЬ СПИДОМ! ТЕПЕРЬ ВАМ ПЕЗДА!" }
//            }
        }
    }
}