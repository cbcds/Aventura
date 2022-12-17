enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    includeBuild("app-build")
    repositories {
        google()
        gradlePluginPortal()
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
rootProject.name = "aventura"
include(":app")
include(":core:data")
include(":core:domain")
include(":core:model")
include(":core:navigation")
include(":core:ui")
include(":feature")
include(":feature:auth")
