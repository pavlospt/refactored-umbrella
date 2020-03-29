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
    implementation(Deps.AndroidX.RecyclerView.RECYCLERVIEW)
}
