import ext.androidCoreModule
import ext.composeProject
import ext.uiModule
import ext.useCaseModule

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android.buildFeatures.compose = true

composeProject()

dependencies {
    api(kotlin("stdlib"))
    implementation(Deps.Kotlinx.Coroutines.CORE)

    api(Deps.Koin.CORE)
    implementation(Deps.Koin.ANDROIDX_VIEWMODEL)

    implementation(androidCoreModule("viewbinding"))
    api(useCaseModule(featureNotation = "github"))
    implementation(uiModule(moduleNotation = "design-system"))
    implementation(uiModule(moduleNotation = "navigation"))
}
