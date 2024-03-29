plugins {
    id("com.cbcds.aventura.lib")
    id("com.cbcds.aventura.hilt")
}

android {
    namespace = "com.cbcds.aventura.core.network"
}

dependencies {
    implementation(project(":core:common"))

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth.ktx)
}
