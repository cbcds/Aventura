buildscript {
    dependencies {
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.22.0-RC3")
        classpath("com.google.gms:google-services:4.3.14")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.detekt) apply true
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    id("com.android.library") version "7.3.1" apply false
}
