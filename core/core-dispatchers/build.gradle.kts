plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(Deps.Kotlinx.Coroutines.CORE)
    implementation(Deps.Kotlinx.Coroutines.ANDROID)

    implementation(Deps.Koin.CORE)
}
