enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs"){
            version("ktor","2.1.3")
            version("coroutine","1.6.4")

            library("client-core","io.ktor","ktor-client-core").versionRef("ktor")
            library("client-cio","io.ktor","ktor-client-cio").versionRef("ktor")
            library("client-content-neg","io.ktor","ktor-client-content-negotiation").versionRef("ktor")
            library("client-json","io.ktor","ktor-serialization-kotlinx-json").versionRef("ktor")
            library("client-test","io.ktor","ktor-client-mock").versionRef("ktor")

            library("coroutine-core","org.jetbrains.kotlinx","kotlinx-coroutines-core").versionRef("coroutine")
            library("coroutine-test","org.jetbrains.kotlinx","kotlinx-coroutines-test").versionRef("coroutine")

            library("sheetsdb","io.github.cherrio-llc:sheets-db:0.0.2")
            library("jsoup","org.jsoup:jsoup:1.15.3")

            library("server-core","io.ktor","ktor-server-core-jvm").versionRef("ktor")
            library("server-host","io.ktor","ktor-server-host-common-jvm").versionRef("ktor")
            library("server-pages","io.ktor","ktor-server-status-pages-jvm").versionRef("ktor")
            library("server-neg","io.ktor","ktor-server-content-negotiation-jvm").versionRef("ktor")
            library("server-logging","io.ktor","ktor-server-call-logging-jvm").versionRef("ktor")
            library("server-json","io.ktor","ktor-serialization-kotlinx-json-jvm").versionRef("ktor")
            library("server-netty","io.ktor","ktor-server-netty-jvm").versionRef("ktor")
            library("log4j","ch.qos.logback:logback-classic:1.2.11")
            library("ecache","org.ehcache:ehcache:3.10.1")

            library("orbit","org.orbit-mvi:orbit-core:4.4.0")
            library("koin","io.insert-koin:koin-core:3.2.2")
            library("voyager-navigation","cafe.adriel.voyager:voyager-navigator:1.0.0-rc03")
            library("splitpane","org.jetbrains.compose.components:components-splitpane-desktop:1.2.1")
            library("vlcj","uk.co.caprica:vlcj:4.7.3")
            library("vlcj-subs","uk.co.caprica:vlcj-subs:0.2.0")
            //Tests
            library("server-test","io.ktor","ktor-server-tests-jvm").versionRef("ktor")
            library("test","org.jetbrains.kotlin:kotlin-test-junit:1.7.22")

        }
    }
}
rootProject.name = "NiteNaija"
include(":server")
include(":desktop")

