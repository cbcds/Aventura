plugins {
    id("com.cbcds.aventura.feature")
    id("com.cbcds.aventura.lib.compose")
}

android {
    namespace = "com.cbcds.aventura.feature.auth"
}

dependencies {
    implementation(libs.accompanist.pager)
}