buildscript {
    dependencies {
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.22.0-RC3")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.detekt) apply true
}
