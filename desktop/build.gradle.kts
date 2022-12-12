import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.serialization")
}

group = "github.cherrio.netnaija"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.client.core)
                implementation(libs.client.cio)
                implementation(libs.client.json)
                implementation(libs.client.content.neg)
                implementation(libs.voyager.navigation)
                implementation(libs.koin)
                implementation(libs.orbit)
                implementation(libs.splitpane)
                implementation(libs.vlcj)
                implementation(libs.vlcj.subs)

                implementation(libs.jsoup)
            }
        }
        val jvmTest by getting{
            dependencies {
                implementation(libs.test)
                implementation(libs.client.test)
                implementation(libs.coroutine.test)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "desktop"
            packageVersion = "1.0.0"
        }
    }
}
