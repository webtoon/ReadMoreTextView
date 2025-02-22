import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.webtoonscorp.android.readmore.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    implementation(libs.android.pluginGradle)
    implementation(libs.kotlin.pluginGradle)
    implementation(libs.compose.compiler.pluginGradle)
    implementation(libs.metalava.pluginGradle)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "readmore.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "readmore.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("kotlinAndroid") {
            id = "readmore.kotlin.android"
            implementationClass = "KotlinAndroidConventionPlugin"
        }
        register("kotlinMultiplatform") {
            id = "readmore.kotlin.multiplatform"
            implementationClass = "KotlinMultiplatformConventionPlugin"
        }
        register("compose") {
            id = "readmore.compose"
            implementationClass = "ComposeMultiplatformConventionPlugin"
        }
        register("androidCompose") {
            id = "readmore.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
        register("metalava") {
            id = "readmore.metalava"
            implementationClass = "MetalavaConventionPlugin"
        }
    }
}
