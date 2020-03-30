import ext.coreModule

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    defaultConfig {
        kapt {
            arguments {
                arg("room.incremental", "true")
            }
        }
    }
}

dependencies {
    kapt(Deps.AndroidX.Room.COMPILER)

    api(kotlin("stdlib"))
    api(coreModule("domain"))
    api(Deps.Kotlinx.Coroutines.CORE)

    implementation(Deps.AndroidX.Room.COMMON)
}
