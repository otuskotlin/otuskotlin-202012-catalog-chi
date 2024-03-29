package ru.ok.catalog

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.features.*
import io.ktor.html.*
import kotlinx.html.*
import io.ktor.http.content.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.serialization.*
import ru.ok.catalog.controllers.MpCategoryApiHttpAdapter
import ru.ok.catalog.controllers.MpClassificationApiHttpAdapter

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

/**
 * Please note that you can use any other name instead of *module*.
 * Also note that you can have more then one modules in your application.
 * */
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    val categoryHttpApi = MpCategoryApiHttpAdapter()
    val classificationHttpApi = MpClassificationApiHttpAdapter()

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
        //host("xxx")
    }

    install(ContentNegotiation) {
        json(
            contentType = ContentType.Application.Json,
            json = jsonConfig,
        )
    }




    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }
    routing {
        install(StatusPages) {
            exception<AuthenticationException> { cause ->
                call.respond(HttpStatusCode.Unauthorized)
            }
            exception<AuthorizationException> { cause ->
                call.respond(HttpStatusCode.Forbidden)
            }

        }
    }
    routing {
        get("/html-dsl") {
            call.respondHtml {
                body {
                    h1 { +"HTML" }
                    ul {
                        for (n in 1..10) {
                            li { +"$n" }
                        }
                    }
                }
            }
        }
    }
    routing {
        // Static feature. Try to access `/static/ktor_logo.svg`
        static("/static") {
            resources("static")
        }

        route("/category") {
            post("/read")   { categoryHttpApi.read(this) }
            post("/create") { categoryHttpApi.create(this) }
            post("/update") { categoryHttpApi.update(this) }
            post("/delete") { categoryHttpApi.delete(this) }
            post("/list")   { categoryHttpApi.list(this) }
        }

        route("/classification") {
            post("/create") { classificationHttpApi.create(this) }
            post("/delete") { classificationHttpApi.delete(this) }
            post("/list")   { classificationHttpApi.list(this) }
        }
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
