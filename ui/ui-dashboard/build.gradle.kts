import ext.androidCoreModule
import ext.composeProject
import ext.dbModule
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

    implementation(Deps.AndroidX.Lifecycle.LIVEDATA_KTX)

    api(Deps.Koin.CORE)
    implementation(Deps.Koin.ANDROIDX_VIEWMODEL)

    api(useCaseModule(featureNotation = "github"))
    implementation(dbModule(featureNotation = "github"))
    implementation(uiModule(moduleNotation = "design-system"))
}
