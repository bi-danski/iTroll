import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
            binaryOption("bundleId", "org.me2you.shared")
        }
    }
    
    android {
       namespace = "org.me2you.itroll.shared"
       compileSdk = libs.versions.android.compileSdk.get().toInt()
       minSdk = libs.versions.android.minSdk.get().toInt()
    
       compilerOptions {
           jvmTarget = JvmTarget.JVM_11
       }
       androidResources {
           enable = true
       }
       withHostTest {
           isIncludeAndroidResources = true
       }
       withDeviceTestBuilder { sourceSetTreeName = "test" }
           .configure {
               instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
           }
    }

    sourceSets {
        androidMain.dependencies {
            api(libs.koin.android)
            api(libs.compose.uiToolingPreview)
            api(libs.androidx.activity.compose)

            implementation(libs.koin.androidx.startup)
            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.appcompat)
            implementation(libs.androidx.core.splashscreen)
            implementation(libs.androidx.datastore.preferences)
            implementation(libs.ktor.client.okhttp)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

        commonMain.dependencies {
            api(project.dependencies.platform(libs.koin.bom))
            api(libs.koin.core)
            api(libs.koin.compose)
            api(libs.koin.compose.viewmodel)

            api(libs.compose.runtime)
            api(libs.compose.foundation)
            api(libs.compose.material3)
            api(libs.compose.ui)
            api(libs.compose.uiToolingPreview)

            api(libs.kotlinx.coroutines.core)
            api(libs.androidx.lifecycle.viewmodelCompose)
            api(libs.androidx.lifecycle.runtimeCompose)
            api(libs.androidx.navigation3.runtime)
            api(libs.jetbrains.navigation3.ui)
            api(libs.jetbrains.lifecycle.viewmodel.navigation3)

            implementation(libs.compose.components.resources)
            implementation(libs.material.icons.core)
            implementation(libs.material.icons.extended)
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.datetime)

            implementation(libs.androidx.datastore.preferences)
            implementation(libs.androidx.datastore.core.okio)
            implementation(libs.androidx.media3.cast)
            implementation(libs.androidx.media3.ui)
            implementation(libs.androidx.media3.exoplayer)
            implementation(libs.androidx.media3.session)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.serialization.json)
            implementation(libs.ktor.content.negotiation)
            implementation(libs.ktor.logging)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
}