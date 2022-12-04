package github.cherrio

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import github.cherrio.plugins.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureMonitoring()
    configureSerialization()
    configureRouting()
}
