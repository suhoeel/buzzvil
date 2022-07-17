package commons

import BuildAndroidConfig
import dependencies.Dependencies
import dependencies.AnnotationProcessorsDependencies
import extensions.implementation

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    kotlin("kapt")
}

android {
    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION
        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
    }

    viewBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.COROUTINES_ANDROID)

//    implementation(Dependencies.TIMBER)


//    testImplementation(project(BuildModules.Libraries.TEST_UTILS))
//    addTestsDependencies()
}