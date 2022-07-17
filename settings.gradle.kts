pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
include(
    ":app",
    ":core",
    ":commons:ui",
    ":commons:view"
)
rootProject.buildFileName = "build.gradle.kts"
//rootProject.name = "Buzzvil"

