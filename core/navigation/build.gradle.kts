plugins {
    id("com.cbcds.aventura.lib")
    id("com.cbcds.aventura.hilt")
}

android {
    namespace = "com.cbcds.aventura.core.navigation"
}

dependencies {
    implementation(libs.androidx.navigation.compose)
}