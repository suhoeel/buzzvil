import dependencies.Dependencies
import dependencies.AnnotationProcessorsDependencies
import extensions.implementation


plugins {
    id("commons.android-library")
}

dependencies {

    implementation(Dependencies.ROOM)
    implementation(Dependencies.ROOM_KTX)

    /*implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.LIFECYCLE_LIVEDATA_KTX)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL_SAVEDSTATE)*/
    implementation(Dependencies.HILT_ANDROID)
    implementation(Dependencies.OKHTTP)
    implementation(Dependencies.OKHTTP_LOGGING)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_GSON_CONVERTER)

    kapt(AnnotationProcessorsDependencies.ROOM_COMPILER)
    kapt(AnnotationProcessorsDependencies.LIFECYCLE_COMPILER)
    kapt(AnnotationProcessorsDependencies.HILT_COMPILER)



//    implementation(Dependencies.LOGGING)
}
