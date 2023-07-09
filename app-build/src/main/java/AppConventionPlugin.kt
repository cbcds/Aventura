import com.android.build.api.dsl.ApplicationExtension
import com.cbcds.aventura.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AppConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("io.gitlab.arturbosch.detekt")
                apply("com.cbcds.aventura.hilt")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlin(this)
                defaultConfig.targetSdk = 33
            }
        }
    }
}
