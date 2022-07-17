import dependencies.Dependencies
import dependencies.AnnotationProcessorsDependencies
import extensions.implementation


plugins {
    id("commons.android-library")
}

dependencies {
//    implementation(Dependencies.ROOM)
//    implementation(Dependencies.ROOM_KTX)

//    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.LIFECYCLE_LIVEDATA_KTX)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL_SAVEDSTATE)

//    implementation(Dependencies.OKHTTP)
//    implementation(Dependencies.OKHTTP_LOGGING)
//    implementation(Dependencies.RETROFIT)
//    implementation(Dependencies.RETROFIT_GSON_CONVERTER)

//    implementation(Dependencies.RECYCLER_VIEW)

//    implementation(Dependencies.NAVIGATION_UI_KTX)
//    implementation(Dependencies.PAGING_KTX)
//    implementation(Dependencies.GLIDE)

//    kapt(AnnotationProcessorsDependencies.ROOM_COMPILER)
//    kapt(AnnotationProcessorsDependencies.LIFECYCLE_COMPILER)
//    kapt(AnnotationProcessorsDependencies.GLIDE_COMPILER)

//    implementation(Dependencies.LOGGING)
}
