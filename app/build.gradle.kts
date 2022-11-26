plugins {
    id("com.cbcds.aventura.app")
    id("com.cbcds.aventura.app.compose")
    id("com.cbcds.aventura.hilt")
    id("io.gitlab.arturbosch.detekt")
}

android {
    namespace = "com.cbcds.aventura"

    defaultConfig {
        applicationId = "com.cbcds.aventura"
        versionCode = 1
        versionName = "1.0.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
        }
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":feature:auth"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
}

kapt {
    correctErrorTypes = true
}