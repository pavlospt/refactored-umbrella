import ext.coreModule
import ext.dbModule
import ext.localRepoModule
import ext.remoteRepoModule

plugins {
    id("com.android.library")
    kotlin("android")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(Deps.Kotlinx.Coroutines.CORE)

    implementation(dbModule("github"))
    implementation(coreModule("domain"))
    implementation(coreModule("dispatchers"))
    implementation(coreModule("usecase"))
    implementation(localRepoModule("github"))
    implementation(remoteRepoModule("github"))
}
