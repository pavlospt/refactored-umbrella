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
    implementation(Deps.Koin.CORE)

    implementation(coreModule("domain"))
    implementation(coreModule("dispatchers"))
    implementation(coreModule("usecase"))
    implementation(dbModule(featureNotation = "github"))
    implementation(localRepoModule(featureNotation = "github"))
    implementation(remoteRepoModule(featureNotation = "github"))
}
