object Deps {
    object AndroidX {
        object Room {
            private const val VERSION = "2.2.5"
            const val COMPILER = "androidx.room:room-compiler:$VERSION"
            const val RUNTIME = "androidx.room:room-runtime:$VERSION"
            const val KTX = "androidx.room:room-ktx:$VERSION"
        }

        object Core {
            private const val VERSION = "1.2.0"
            const val KTX = "androidx.core:core-ktx:$VERSION"
        }

        object ConstraintLayout {
            private const val VERSION = "2.0.0-beta4"
            const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:$VERSION"
        }

        object Navigation {
            private const val VERSION = "2.2.1"
            const val FRAGMENT = "androidx.navigation:navigation-fragment:$VERSION"
            const val FRAGMENT_KTX = "androidx.navigation:navigation-fragment-ktx:$VERSION"
            const val UI = "androidx.navigation:navigation-ui:$VERSION"
            const val UI_KTX = "androidx.navigation:navigation-ui-ktx:$VERSION"
        }

        object Lifecycle {
            private const val VERSION = "2.2.0"
            const val EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:$VERSION"
            const val RUNTIME = "androidx.lifecycle:lifecycle-runtime:$VERSION"
            const val RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:$VERSION"
            const val COMMON_JAVA8 = "androidx.lifecycle:lifecycle-common-java8:$VERSION"
            const val VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:$VERSION"
            const val LIVEDATA_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:$VERSION"
        }

        object RecyclerView {
            private const val VERSION = "1.2.0-alpha01"
            const val RECYCLERVIEW = "androidx.recyclerview:recyclerview:$VERSION"
        }
    }

    object Google {
        object Material {
            private const val VERSION = "1.2.0-alpha05"
            const val MATERIAL = "com.google.android.material:material:$VERSION"
        }
    }

    object Kotlinx {
        object Coroutines {
            private const val VERSION = SharedVersions.Kotlinx.COROUTINES
            const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$VERSION"
            const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$VERSION"
        }
    }

    object Square {
        object OkHttp {
            private const val VERSION = "4.4.0"
            const val OKHTTP = "com.squareup.okhttp3:okhttp:$VERSION"
            const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:$VERSION"
        }

        object Retrofit {
            private const val VERSION = "2.7.1"
            const val RETROFIT = "com.squareup.retrofit2:retrofit:$VERSION"

            object Converters {
                const val MOSHI = "com.squareup.retrofit2:converter-moshi:$VERSION"
            }
        }

        object Moshi {
            private const val VERSION = "1.9.2"
            const val KOTLIN = "com.squareup.moshi:moshi-kotlin:$VERSION"
            const val KOTLIN_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:$VERSION"
            const val ADAPTERS = "com.squareup.moshi:moshi-adapters:$VERSION"
        }
    }

    object Koin {
        private const val VERSION = "2.1.5"
        const val CORE = "org.koin:koin-core:$VERSION"
        const val CORE_EXT = "org.koin:koin-core-ext:$VERSION"
        const val ANDROID = "org.koin:koin-android:$VERSION"
        const val ANDROID_EXT = "org.koin:koin-android-ext:$VERSION"
        const val ANDROIDX_SCOPE = "org.koin:koin-androidx-scope:$VERSION"
        const val ANDROIDX_VIEWMODEL = "org.koin:koin-androidx-viewmodel:$VERSION"
        const val ANDROIDX_FRAGMENT = "org.koin:koin-androidx-fragment:$VERSION"
        const val ANDROIDX_EXT = "org.koin:koin-androidx-ext:$VERSION"
    }

    object Coil {
        private const val VERSION = "0.9.5"
        const val COIL = "io.coil-kt:coil:$VERSION"
    }

    object FlowBinding {
        private const val VERSION = "0.10.2"
        const val ANDROID = "io.github.reactivecircus.flowbinding:flowbinding-android:$VERSION"
        const val SWIPE_REFRESH_LAYOUT =
            "io.github.reactivecircus.flowbinding:flowbinding-swiperefreshlayout:$VERSION"
    }
}
