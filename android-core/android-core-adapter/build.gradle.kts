plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    buildFeatures.viewBinding = true
}

dependencies {
    implementation(kotlin("stdlib"))
    api(Deps.AndroidX.RecyclerView.RECYCLERVIEW)
}
