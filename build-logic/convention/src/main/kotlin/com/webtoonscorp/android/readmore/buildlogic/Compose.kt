package com.webtoonscorp.android.readmore.buildlogic

import org.gradle.api.Project

fun Project.configureCompose() {
    pluginManager.apply("org.jetbrains.compose")
    pluginManager.apply("org.jetbrains.kotlin.plugin.compose")
}
