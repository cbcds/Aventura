plugins {
    id("com.cbcds.aventura.feature")
    id("com.cbcds.aventura.lib.compose")
    alias(libs.plugins.secrets)
}

android {
    namespace = "com.cbcds.aventura.feature.auth"
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.androidx.compose.foundation)
    implementation(libs.play.services.auth)
    implementation(libs.facebook.login)
}
