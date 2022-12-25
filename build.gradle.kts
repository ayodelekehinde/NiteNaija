
plugins {
    kotlin("jvm") version "1.7.20" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.22" apply false
    id("io.ktor.plugin") version "2.1.3" apply false
    kotlin("multiplatform").version("1.7.20").apply(false)
    id("org.jetbrains.compose").version("1.2.2").apply(false)
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}