plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace= "com.cbcds.aventura.feature.auth"
    compileSdk = 33

    defaultConfig {
        minSdk = 23
        targetSdk = 33

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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-rc01"
    }
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:ui"))

    implementation(libs.androidx.core.ktx)
    api(libs.androidx.compose.material3)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.accompanist.pager)

    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.compiler)

    debugImplementation(libs.androidx.compose.ui.tooling)
}