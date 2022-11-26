plugins {
    id("com.cbcds.aventura.lib")
    id("com.cbcds.aventura.lib.compose")
}

android {
    namespace = "com.cbcds.aventura.core.ui"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    api(libs.androidx.compose.material3)
    debugApi(libs.androidx.compose.ui.tooling)
}