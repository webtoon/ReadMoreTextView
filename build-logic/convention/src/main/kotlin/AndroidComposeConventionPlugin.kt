import com.webtoonscorp.android.readmore.buildlogic.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureCompose()
        }
    }
}
