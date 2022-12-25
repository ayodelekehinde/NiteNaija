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
            kotlinOptions.jvmTarget = "17"
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
            modules("java.instrument", "java.management", "jdk.unsupported")
            targetFormats(TargetFormat.Dmg, TargetFormat.Exe)
            packageName = "NiteFlix"
            packageVersion = "1.0.0"

            macOS {
                iconFile.set(project.file("../icons/icon.icns"))
                dockName = "NiteFlix"
                bundleID = "com.cherrio.niteflix"
            }
            windows {
                iconFile.set(project.file("../icons/icon.ico"))
            }
        }
        buildTypes.release.proguard {
            obfuscate.set(true)
            configurationFiles.from("rules.pro")
        }
    }
}