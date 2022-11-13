plugins {
    alias(libs.plugins.android.library)
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.cbcds.aventura.core.data"
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8

    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":core:model"))

    implementation(libs.androidx.core.ktx)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}