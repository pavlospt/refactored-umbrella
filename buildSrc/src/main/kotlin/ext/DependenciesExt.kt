package ext

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

fun DependencyHandler.androidCoreModule(moduleNotation: String) =
    project(":android-core-$moduleNotation")

fun DependencyHandler.coreModule(moduleNotation: String) =
    project(":core-$moduleNotation")
