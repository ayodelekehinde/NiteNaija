package github.cherrio

import github.cherrio.plugins.configureMonitoring
import github.cherrio.plugins.configureRouting
import github.cherrio.plugins.configureSerialization
import github.cherrio.plugins.configureStatusPages
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureMonitoring()
    configureSerialization()
    configureRouting()
    configureStatusPages()
}
