plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    viewBinding.isEnabled = true
}

dependencies {
    implementation(kotlin("stdlib"))
    api(Deps.AndroidX.RecyclerView.RECYCLERVIEW)
}
