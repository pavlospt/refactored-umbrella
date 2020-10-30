import ext.androidCoreModule
import ext.composeLibrary
import ext.coreModule
import ext.dbModule
import ext.uiModule
import ext.useCaseModule

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    buildFeatures.viewBinding = true
    buildFeatures.compose = true
}

composeLibrary()

dependencies {
    api(kotlin("stdlib"))
    implementation(Deps.Kotlinx.Coroutines.CORE)

    api(Deps.AndroidX.Fragment.FRAGMENT)
    api(Deps.AndroidX.ConstraintLayout.CONSTRAINT_LAYOUT)

    implementation(Deps.AndroidX.Lifecycle.RUNTIME_KTX)
    implementation(Deps.AndroidX.Lifecycle.COMMON)
    implementation(Deps.AndroidX.Lifecycle.VIEWMODEL_KTX)

    api(Deps.AndroidX.Lifecycle.VIEWMODEL)
    api(Deps.AndroidX.Lifecycle.LIVEDATA_CORE)

    api(Deps.Google.Material.MATERIAL)

    implementation(Deps.FlowBinding.ANDROID)

    api(Deps.Koin.CORE)
    implementation(Deps.Koin.ANDROIDX_VIEWMODEL)

    implementation(androidCoreModule("viewbinding"))
    api(useCaseModule(featureNotation = "github"))
    implementation(uiModule(moduleNotation = "design-system"))
}
