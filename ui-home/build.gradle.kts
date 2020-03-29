import ext.androidCoreModule
import ext.coreModule
import ext.dbModule
import ext.useCaseModule

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    viewBinding.isEnabled = true
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(Deps.AndroidX.Fragment.FRAGMENT)
    implementation(Deps.AndroidX.ConstraintLayout.CONSTRAINT_LAYOUT)
    implementation(Deps.AndroidX.Lifecycle.RUNTIME)
    implementation(Deps.AndroidX.Lifecycle.RUNTIME_KTX)
    implementation(Deps.AndroidX.Lifecycle.COMMON_JAVA8)
    implementation(Deps.AndroidX.Lifecycle.VIEWMODEL_KTX)
    implementation(Deps.AndroidX.Lifecycle.LIVEDATA_KTX)
    implementation(Deps.AndroidX.Lifecycle.EXTENSIONS)

    implementation(Deps.Google.Material.MATERIAL)

    implementation(Deps.FlowBinding.ANDROID)

    implementation(Deps.Koin.CORE)
    implementation(Deps.Koin.CORE_EXT)
    implementation(Deps.Koin.ANDROID)
    implementation(Deps.Koin.ANDROID_EXT)
    implementation(Deps.Koin.ANDROIDX_SCOPE)
    implementation(Deps.Koin.ANDROIDX_VIEWMODEL)
    implementation(Deps.Koin.ANDROIDX_FRAGMENT)
    implementation(Deps.Koin.ANDROIDX_EXT)

    implementation(androidCoreModule("viewbinding"))
    implementation(coreModule("usecase"))
    implementation(coreModule("domain"))
    implementation(dbModule("github"))
    implementation(useCaseModule("github"))
}
