plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(Deps.Kotlinx.Coroutines.CORE)

    implementation(Deps.Koin.CORE)
}
