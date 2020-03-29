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

    implementation(kotlin("stdlib-jdk8"))
    implementation(coreModule("domain"))

    implementation(Deps.AndroidX.Room.RUNTIME)
    implementation(Deps.AndroidX.Room.KTX)
}
