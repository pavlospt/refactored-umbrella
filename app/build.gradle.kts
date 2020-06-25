import ext.coreModule
import ext.dbModule
import ext.localRepoModule
import ext.remoteRepoModule
import ext.uiModule
import ext.useCaseModule

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    defaultConfig {
        applicationId = "com.github.pavlospt.refactoredumbrella"
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        kapt {
            arguments {
                arg("room.incremental", "true")
            }
        }
    }

    buildFeatures.viewBinding = true

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-rules.pro")
        }
    }
}

dependencies {
    kapt(Deps.AndroidX.Room.COMPILER)

    implementation(kotlin("stdlib"))

    implementation(coreModule("dispatchers"))

    // Github core stack
    implementation(dbModule(featureNotation = "github"))
    implementation(localRepoModule(featureNotation = "github"))
    implementation(remoteRepoModule(featureNotation = "github"))
    implementation(useCaseModule(featureNotation = "github"))

    // Dashboard feature
    implementation(uiModule("dashboard"))
    // Home feature
    implementation(uiModule("home"))

    implementation(Deps.Kotlinx.Coroutines.CORE)

    implementation(Deps.AndroidX.ConstraintLayout.CONSTRAINT_LAYOUT)

    implementation(Deps.AndroidX.AppCompat.APPCOMPAT)
    implementation(Deps.AndroidX.Navigation.FRAGMENT) { because("We use defaultNavHost") }
    implementation(Deps.AndroidX.Navigation.UI)
    implementation(Deps.AndroidX.Navigation.UI_KTX)
    implementation(Deps.AndroidX.Navigation.RUNTIME)
    implementation(Deps.AndroidX.Navigation.RUNTIME_KTX)
    implementation(Deps.AndroidX.Room.RUNTIME)
    implementation(Deps.AndroidX.Room.COMMON)
    implementation(Deps.AndroidX.Room.ROOM_KTX)

    implementation(Deps.Google.Material.MATERIAL)

    implementation(Deps.Square.OkHttp.OKHTTP)
    implementation(Deps.Square.OkHttp.LOGGING_INTERCEPTOR)
    implementation(Deps.Square.Retrofit.RETROFIT)
    implementation(Deps.Square.Retrofit.Converters.MOSHI)
    implementation(Deps.Square.Moshi.MOSHI)
    implementation(Deps.Square.Moshi.ADAPTERS)

    implementation(Deps.Koin.CORE)
    implementation(Deps.Koin.ANDROID)

    // Test Deps
    testImplementation(TestDeps.JUnit.JUNIT)
    testImplementation(TestDeps.Kotlinx.COROUTINES_TEST)
    testImplementation(TestDeps.AndroidX.Arch.CORE_TESTING)
}
