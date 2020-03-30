import ext.coreModule
import ext.dbModule
import ext.localRepoModule
import ext.remoteRepoModule

plugins {
    id("com.android.library")
    kotlin("android")
}

dependencies {
    api(kotlin("stdlib"))
    api(Deps.Koin.CORE)
    api(Deps.Kotlinx.Coroutines.CORE)

    api(coreModule("domain"))
    api(coreModule("dispatchers"))
    api(coreModule("usecase"))
    api(dbModule(featureNotation = "github"))
    api(remoteRepoModule(featureNotation = "github"))
    api(localRepoModule(featureNotation = "github"))
}
