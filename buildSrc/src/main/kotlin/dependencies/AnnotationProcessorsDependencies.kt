package dependencies

object AnnotationProcessorsDependencies {
    const val HILT_COMPILER = "com.google.dagger:hilt-compiler:${BuildDependenciesVersions.HILT}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${BuildDependenciesVersions.GLIDE}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${BuildDependenciesVersions.ROOM}"
    const val LIFECYCLE_COMPILER = "androidx.lifecycle:lifecycle-compiler:${BuildDependenciesVersions.LIFECYCLE}"
}