import ext.dbModule

plugins {
    id("com.android.library")
    kotlin("android")
}

dependencies {
    api(kotlin("stdlib"))
    api(Deps.Kotlinx.Coroutines.CORE)
    api(dbModule(featureNotation = "github"))
}
