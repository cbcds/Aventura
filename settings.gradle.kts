enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    includeBuild("app-build")
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

rootProject.name = "aventura"
include(":app")
include(":core:common")
include(":core:data")
include(":core:domain")
include(":core:model")
include(":core:navigation")
include(":core:network")
include(":core:ui")
include(":core:user")
include(":feature:auth")
