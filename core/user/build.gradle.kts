plugins {
    id("com.cbcds.aventura.lib")
}

android {
    namespace = "com.cbcds.aventura.core.user"
}

dependencies {
    implementation(project(":core:model"))

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth.ktx)
}