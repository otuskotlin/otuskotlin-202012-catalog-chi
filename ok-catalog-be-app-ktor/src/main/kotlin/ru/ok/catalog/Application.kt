package ru.ok.catalog

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.features.*
import io.ktor.html.*
import kotlinx.html.*
import io.ktor.content.*
import io.ktor.http.content.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.serialization.*
import ru.ok.catalog.controllers.MpCategoryController
import ru.ok.catalog.controllers.MpClassificationController
import ru.ok.catalog.transport.kmp.models.category.*
import ru.ok.catalog.transport.kmp.models.classification.MpRequestClassificationCreate
import ru.ok.catalog.transport.kmp.models.classification.MpRequestClassificationDelete
import ru.ok.catalog.transport.kmp.models.classification.MpRequestClassificationList
import ru.ok.catalog.transport.kmp.models.common.MpMessage

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

/**
 * Please note that you can use any other name instead of *module*.
 * Also note that you can have more then one modules in your application.
 * */
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    val categoryControler = MpCategoryController()
    val classificationControler = MpClassificationController()

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
            post("/read") {
                categoryControler.read(this)
//                try {
//                    val query = call.receive<MpMessage>() as MpRequestCategoryRead
//                } catch (e: Throwable) {
//
//                }
            }
            post("/create") {
                try {
                    val query = call.receive<MpMessage>() as MpRequestCategoryCreate
                } catch (e: Throwable) {

                }
            }
            post("/update") {
                try {
                    val query = call.receive<MpMessage>() as MpRequestCategoryUpdate
                } catch (e: Throwable) {

                }
            }
            post("/delete") {
                try {
                    val query = call.receive<MpMessage>() as MpRequestCategoryDelete
                } catch (e: Throwable) {

                }
            }
            post("/list") {
                try {
                    val query = call.receive<MpMessage>() as MpRequestCategoryList
                } catch (e: Throwable) {

                }
            }
        }

        route("/classification") {
            //post("/read") { }
            post("/create") {
                try {
                    val query = call.receive<MpMessage>() as MpRequestClassificationCreate
                } catch (e: Throwable) {

                }
            }
            //post("/update") {}
            post("/delete") {
                try {
                    val query = call.receive<MpMessage>() as MpRequestClassificationDelete
                } catch (e: Throwable) {

                }
            }
            post("/list") {
                try {
                    val query = call.receive<MpMessage>() as MpRequestClassificationList
                } catch (e: Throwable) {

                }
            }
        }
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
