
plugins {
    application
    kotlin("jvm")
    id("io.ktor.plugin")
    id("org.jetbrains.kotlin.plugin.serialization")
}

group = "github.cherrio"
version = "0.0.1"
application {
    mainClass.set("github.cherrio.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    //Server
    implementation(libs.server.core)
    implementation(libs.server.host)
    implementation(libs.server.pages)
    implementation(libs.server.neg)
    implementation(libs.server.logging)
    implementation(libs.server.json)
    implementation(libs.server.netty)
    implementation(libs.log4j)

    //Client
    implementation(libs.client.core)
    implementation(libs.client.cio)
    implementation(libs.client.json)
    implementation(libs.client.content.neg)

    implementation(libs.sheetsdb)
    implementation(libs.jsoup)

    //Tests
    testImplementation(libs.server.test)
    testImplementation(libs.test)
    testImplementation(libs.client.test)

}