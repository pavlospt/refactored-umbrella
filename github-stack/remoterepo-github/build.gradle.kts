import ext.coreModule

plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    kapt(Deps.Square.Moshi.KOTLIN_CODEGEN)

    api(kotlin("stdlib"))
    implementation(Deps.Square.Retrofit.RETROFIT)
    implementation(Deps.Square.Retrofit.Converters.MOSHI)
    implementation(Deps.Square.Moshi.KOTLIN)
    implementation(Deps.Square.Moshi.ADAPTERS)

    implementation(coreModule("domain"))
}
