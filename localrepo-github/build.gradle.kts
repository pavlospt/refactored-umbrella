import ext.dbModule

plugins {
    id("com.android.library")
    kotlin("android")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(Deps.Kotlinx.Coroutines.CORE)
    implementation(dbModule("github"))
}
