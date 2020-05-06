import ext.coreModule

plugins {
    id("com.android.library")
    kotlin("android")
}

dependencies {
    api(kotlin("stdlib"))
    api(coreModule("domain"))
    api(Deps.Kotlinx.Coroutines.CORE)

    implementation(Deps.AndroidX.Room.COMMON)
}
