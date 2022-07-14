import dependencies.Dependencies


plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
    kotlin(BuildPlugins.KAPT)
    id(BuildPlugins.HILT)
    id(BuildPlugins.NAVIGATION_SAFE_ARGS)
}

android {
    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION
        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME
        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
        }

        getByName(BuildType.DEBUG) {
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
        }
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
        getByName("androidTest") {
            java.srcDir("src/androidTest/kotlin")
        }
    }

    viewBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.HILT)
//    implementation(Dependencies.CONSTRAIN_LAYOUT)
//    implementation(Dependencies.CONSTRAIN_LAYOUT)
    kapt(Dependencies.HILT_COMPILER)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}


/*


android {
    compileSdk 32

    defaultConfig {
        applicationId "com.buzzvil.campaign"
        minSdk 21
        targetSdk 32
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

    flavorDimensions "target"
    productFlavors {
        def STRING = "String"
        def API_SERVER_TEST = "API_SERVER_TEST"

        dev {
            dimension "target"
            applicationIdSuffix = ".debug"
            buildConfigField(STRING, API_SERVER_TEST, "\"https://s3-ap-northeast-1.amazonaws.com/buzzvi.test/\"")
        }

    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
    viewBinding {
        enabled true
    }
}

dependencies {

    implementation 'androidx.core:core-ktx:1.8.0'
    implementation 'androidx.appcompat:appcompat:1.4.2'
    implementation 'com.google.android.material:material:1.6.1'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation "androidx.viewpager2:viewpager2:1.0.0"

    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'

    implementation 'androidx.core:core-splashscreen:1.0.0-rc01'

    // view model and live data
    def lifecycle_version = "2.4.1"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.5.0"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-extensions:2.2.0"
//    kapt 'androidx.lifecycle:lifecycle-compiler:2.4.1'

    // Kotlin components
    def coroutine_version = "1.6.0"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutine_version"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutine_version"

    */
/*
     Copyright (C) 2018 Aidan Follestad
     Licensed under the Apache License Version 2.0
     https://github.com/afollestad/material-dialogs
     *//*

    def material_dialog_version = "3.2.1"
    implementation "com.afollestad.material-dialogs:core:$material_dialog_version"
    implementation "com.afollestad.material-dialogs:input:$material_dialog_version"

    implementation 'androidx.recyclerview:recyclerview:1.2.1'
    implementation 'androidx.cardview:cardview:1.0.0'

    // Glide
    implementation 'com.github.bumptech.glide:glide:4.13.2'
    kapt 'com.github.bumptech.glide:compiler:4.13.2'

    //OkHttp
    implementation 'com.squareup.okhttp3:okhttp:4.9.3'
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")

    // retrofit
    def retrofit_version = "2.9.0"
    implementation "com.squareup.retrofit2:retrofit:$retrofit_version"
    implementation "com.squareup.retrofit2:converter-gson:$retrofit_version"
    implementation 'com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2'

    def room_version = "2.4.2"
    implementation "androidx.room:room-ktx:$room_version"
    kapt "androidx.room:room-compiler:$room_version"

//    def paging_version = "3.1.1"
//    implementation "androidx.paging:paging-runtime-ktx:$paging_version"

    def nav_version = "2.5.0"
    implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
    implementation "androidx.navigation:navigation-ui-ktx:$nav_version"
    implementation "androidx.core:core-ktx:1.8.0"

    def hilt_version = "2.38.1"
    implementation "com.google.dagger:hilt-android:$hilt_version"
    kapt "com.google.dagger:hilt-compiler:$hilt_version"
//    implementation "androidx.hilt:hilt-lifecycle-viewmodel:1.1.0"

}*/
