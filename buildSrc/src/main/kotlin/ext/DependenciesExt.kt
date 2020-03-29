package ext

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

fun DependencyHandler.androidCoreModule(moduleNotation: String) =
    project(":android-core-$moduleNotation")

fun DependencyHandler.coreModule(moduleNotation: String) =
    project(":core-$moduleNotation")

fun DependencyHandler.dbModule(moduleNotation: String) =
    project(":db-$moduleNotation")

fun DependencyHandler.localRepoModule(moduleNotation: String) =
    project(":localrepo-$moduleNotation")

fun DependencyHandler.remoteRepoModule(moduleNotation: String) =
    project(":remoterepo-$moduleNotation")

fun DependencyHandler.useCaseModule(moduleNotation: String) =
    project(":usecase-$moduleNotation")

fun DependencyHandler.uiModule(moduleNotation: String) =
    project(":ui-$moduleNotation")
