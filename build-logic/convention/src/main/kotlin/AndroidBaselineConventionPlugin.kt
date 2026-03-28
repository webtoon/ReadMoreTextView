import com.webtoonscorp.android.readmore.buildlogic.configureAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidBaselineConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("com.dropbox.dependency-guard")
                apply("io.github.fornewid.manifest-shield")
            }
            configureAndroid()
        }
    }
}
