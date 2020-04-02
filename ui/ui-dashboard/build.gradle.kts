import ext.androidCoreModule
import ext.dbModule
import ext.useCaseModule

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    buildFeatures.viewBinding = true
}

dependencies {
    api(kotlin("stdlib"))
    implementation(Deps.Kotlinx.Coroutines.CORE)

    api(Deps.AndroidX.Fragment.FRAGMENT)
    api(Deps.AndroidX.RecyclerView.RECYCLERVIEW)
    api(Deps.AndroidX.SwipeRefreshLayout.SWIPE_REFRESH_LAYOUT)
    api(Deps.AndroidX.ConstraintLayout.CONSTRAINT_LAYOUT)

    api(Deps.AndroidX.Lifecycle.VIEWMODEL)
    api(Deps.AndroidX.Lifecycle.LIVEDATA_CORE)
    implementation(Deps.AndroidX.Lifecycle.RUNTIME_KTX)
    implementation(Deps.AndroidX.Lifecycle.VIEWMODEL_KTX)
    implementation(Deps.AndroidX.Lifecycle.LIVEDATA_KTX)
    implementation(Deps.AndroidX.Lifecycle.COMMON)

    implementation(Deps.FlowBinding.SWIPE_REFRESH_LAYOUT)

    api(Deps.Koin.CORE)
    implementation(Deps.Koin.ANDROIDX_VIEWMODEL)

    implementation(Deps.Coil.COIL)
    implementation(Deps.Coil.COIL_BASE)

    api(useCaseModule(featureNotation = "github"))
    api(androidCoreModule(moduleNotation = "adapter"))
    implementation(dbModule(featureNotation = "github"))
    implementation(androidCoreModule(moduleNotation = "viewbinding"))
}
