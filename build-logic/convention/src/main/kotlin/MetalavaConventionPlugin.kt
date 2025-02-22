import me.tylerbwong.gradle.metalava.extension.MetalavaExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension

class MetalavaConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("me.tylerbwong.gradle.metalava")
        }

        metalava {
            filename.set("api/current.api")
            sourcePaths.setFrom(
                target.kotlin.sourceSets
                    .filterNot { it.name.contains("test", ignoreCase = true) }
                    .flatMap { it.kotlin.sourceDirectories },
            )
        }
    }
}

private fun Project.metalava(action: MetalavaExtension.() -> Unit) =
    extensions.configure<MetalavaExtension>(action)

internal val Project.kotlin: KotlinProjectExtension
    get() = extensions.getByType<KotlinProjectExtension>()
