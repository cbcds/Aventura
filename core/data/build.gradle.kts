plugins {
    id("com.cbcds.aventura.lib")
    id("com.cbcds.aventura.hilt")
}

android {
    namespace = "com.cbcds.aventura.core.data"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:network"))

    implementation(libs.androidx.core.ktx)
}