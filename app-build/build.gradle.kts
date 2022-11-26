plugins {
    `kotlin-dsl`
}

group = "com.cbcds.aventura.appbuild"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("application") {
            id = "com.cbcds.aventura.app"
            implementationClass = "AppConventionPlugin"
        }
        register("applicationCompose") {
            id = "com.cbcds.aventura.app.compose"
            implementationClass = "AppComposeConventionPlugin"
        }
        register("library") {
            id = "com.cbcds.aventura.lib"
            implementationClass = "LibConventionPlugin"
        }
        register("libraryCompose") {
            id = "com.cbcds.aventura.lib.compose"
            implementationClass = "LibComposeConventionPlugin"
        }
        register("androidFeature") {
            id = "com.cbcds.aventura.feature"
            implementationClass = "FeatureConventionPlugin"
        }
        register("hilt") {
            id = "com.cbcds.aventura.hilt"
            implementationClass = "HiltConventionPlugin"
        }
    }
}