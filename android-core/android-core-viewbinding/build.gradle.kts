plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    viewBinding.isEnabled = true
}

dependencies {
    api(kotlin("stdlib"))
    api(Deps.AndroidX.AppCompat.APPCOMPAT)
    api(Deps.AndroidX.Fragment.FRAGMENT)
    implementation(Deps.AndroidX.Lifecycle.COMMON)
    implementation(Deps.AndroidX.Lifecycle.COMMON_JAVA8)
    implementation(Deps.AndroidX.Lifecycle.LIVEDATA_CORE)
    implementation(Deps.AndroidX.Lifecycle.LIVEDATA_CORE_KTX)
}
