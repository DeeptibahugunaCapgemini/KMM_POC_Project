import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.gradle.api.publish.maven.MavenPublication
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework



group = "com.mcdonalds.kmmagentcore"
version =  "0.0.1-SNAPSHOT"
var releaseOrDebug = "debug"

val libName = "AgenticOrdeingKMM"
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    id("maven-publish")
}


kotlin {

    // ✅ ANDROID TARGET
    androidTarget {
        publishLibraryVariants("release")

        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }

    // ✅ iOS TARGETS
    val xcFramework = XCFramework(libName)

//    iosX64()
    iosArm64(){
        binaries{
            framework{
                baseName = libName
                xcFramework.add(this)
            }
        }
    }
    iosSimulatorArm64(){
        binaries{
            framework{
                baseName = libName
                xcFramework.add(this)
            }
        }
    }
    iosX64{
        binaries{
            framework{
                baseName = libName
                xcFramework.add(this)
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.serialization.json)

                // Ktor multiplatform HTTP client
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.ktor.client.logging)
            }
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}
publishing {
    if (project.hasProperty("args")) {
        releaseOrDebug = project.property("args").toString()
    }
    repositories {
        var repoKey = "agentic-ordering-kmm-snapshot-android"

        if (releaseOrDebug == "release") {
            repoKey = "agentic-ordering-kmm-release-snapshot-android"
        }

        println("Property selected Repo $repoKey")
        maven("https://mcd.jfrog.io/artifactory/${repoKey}") {
            credentials {
                username = providers.gradleProperty("username").orNull
                password = providers.gradleProperty("Password").orNull

            }
        }
    }
}

afterEvaluate {
    configure<PublishingExtension> {
        publications.all {
            val mavenPublication = this as? MavenPublication
            mavenPublication?.artifactId =
                "${project.name}${
                    "-$name".replace("-androidRelease", "-android")
                        .replace("-androidDebug", "-android-debug")
                        .takeUnless { "kotlinMultiplatform" in name }.orEmpty()
                }"
        }
    }
}
android {
    namespace = "com.mcdonalds.kmmagentcore"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
