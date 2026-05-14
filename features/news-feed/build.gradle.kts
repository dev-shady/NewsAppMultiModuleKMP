plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrains.kotlin.plugin.serialization)
    alias(libs.plugins.skie)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "features-news-feed"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:network"))
            implementation(project(":core:database"))
            implementation(libs.sqldelight.coroutines.extensions)
            implementation(libs.koin.core)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.core)
        }
        androidMain.dependencies {
            implementation(libs.androidx.lifecycle.viewmodel.compose)
        }
    }
}

android {
    namespace = "com.devshady.newsappmultimodulekmp.features.newsfeed"
    compileSdk = 37
    defaultConfig {
        minSdk = 24
    }
}
