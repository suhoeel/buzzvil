package dependencies

object Dependencies {

    const val CORE_KTX = "androidx.core:core-ktx:${BuildDependenciesVersions.CORE_KTX}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${BuildDependenciesVersions.APPCOMPAT}"
    const val MATERIAL = "com.google.android.material:material:${BuildDependenciesVersions.MATERIAL}"
    const val CONSTRAIN_LAYOUT = "androidx.constraintlayout:constraintlayout:${BuildDependenciesVersions.CONSTRAIN_LAYOUT}"
    const val HILT = "com.google.dagger:hilt-android:${BuildDependenciesVersions.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-compiler:${BuildDependenciesVersions.HILT}"

    const val VIEW_PAGER = ""
    const val SPLASH_SCREEN= ""
    const val COROUTINE = ""
    const val COROUTINE_CORE = ""
//    const val  = ""
//    const val  = ""


}

/*
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