package ext

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

fun DependencyHandler.androidCoreModule(moduleNotation: String) =
    project(":android-core:android-core-$moduleNotation")

fun DependencyHandler.coreModule(moduleNotation: String) =
    project(":core:core-$moduleNotation")

fun DependencyHandler.dbModule(featureNotation: String) =
    project(":$featureNotation-stack:db-$featureNotation")

fun DependencyHandler.localRepoModule(featureNotation: String) =
    project(":$featureNotation-stack:localrepo-$featureNotation")

fun DependencyHandler.remoteRepoModule(featureNotation: String) =
    project(":$featureNotation-stack:remoterepo-$featureNotation")

fun DependencyHandler.useCaseModule(featureNotation: String) =
    project(":$featureNotation-stack:usecase-$featureNotation")

fun DependencyHandler.uiModule(moduleNotation: String) =
    project(":ui:ui-$moduleNotation")
