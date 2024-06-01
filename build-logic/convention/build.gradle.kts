plugins {
    `kotlin-dsl`
}

group = "com.webtoonscorp.android.readmore.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.android.pluginGradle)
    implementation(libs.kotlin.pluginGradle)
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
        register("androidCompose") {
            id = "readmore.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
    }
}
