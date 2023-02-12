plugins {
    id("com.cbcds.aventura.lib")
    id("com.cbcds.aventura.hilt")
}

android {
    namespace = "com.cbcds.aventura.core.domain"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:data"))

    implementation(libs.androidx.core.ktx)
}