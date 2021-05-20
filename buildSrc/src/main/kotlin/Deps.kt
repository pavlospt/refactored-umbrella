object Deps {
    object AndroidX {
        object Room {
            const val COMPILER = "androidx.room:room-compiler:_"
            const val RUNTIME = "androidx.room:room-runtime:_"
            const val COMMON = "androidx.room:room-common:_"
            const val ROOM_KTX = "androidx.room:room-ktx:_"
        }

        object ConstraintLayout {
            const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:_"
        }

        object Navigation {
            const val FRAGMENT = "androidx.navigation:navigation-fragment:_"
            const val UI = "androidx.navigation:navigation-ui:_"
            const val UI_KTX = "androidx.navigation:navigation-ui-ktx:_"
            const val RUNTIME = "androidx.navigation:navigation-runtime:_"
            const val RUNTIME_KTX = "androidx.navigation:navigation-runtime-ktx:_"
        }

        object Lifecycle {
            const val COMMON = "androidx.lifecycle:lifecycle-common:_"
            const val RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:_"
            const val COMMON_JAVA8 = "androidx.lifecycle:lifecycle-common-java8:_"
            const val VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel:_"
            const val VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:_"
            const val LIVEDATA_CORE = "androidx.lifecycle:lifecycle-livedata-core:_"
            const val LIVEDATA_CORE_KTX = "androidx.lifecycle:lifecycle-livedata-core-ktx:_"
            const val LIVEDATA_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:_"
        }

        object RecyclerView {
            const val RECYCLERVIEW = "androidx.recyclerview:recyclerview:_"
        }

        object Fragment {
            const val FRAGMENT = "androidx.fragment:fragment:_"
        }

        object AppCompat {
            const val APPCOMPAT = "androidx.appcompat:appcompat:_"
        }

        object SwipeRefreshLayout {
            const val SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:_"
        }
    }

    object Google {
        object Material {
            const val MATERIAL = "com.google.android.material:material:_"
        }
    }

    object Kotlinx {
        object Coroutines {
            const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:_"
            const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:_"
        }
    }

    object Square {
        object OkHttp {
            const val OKHTTP = "com.squareup.okhttp3:okhttp:_"
            const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:_"
        }

        object Retrofit {
            const val RETROFIT = "com.squareup.retrofit2:retrofit:_"

            object Converters {
                const val MOSHI = "com.squareup.retrofit2:converter-moshi:_"
            }
        }

        object Moshi {
            const val MOSHI = "com.squareup.moshi:moshi:_"
            const val KOTLIN = "com.squareup.moshi:moshi-kotlin:_"
            const val KOTLIN_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:_"
            const val ADAPTERS = "com.squareup.moshi:moshi-adapters:_"
        }
    }

    object Koin {
        const val CORE = "io.insert-koin:koin-core-jvm:_"
        const val ANDROID = "io.insert-koin:koin-android:_"
    }

    object Coil {
        const val COIL = "io.coil-kt:coil:_"
        const val COIL_BASE = "io.coil-kt:coil-base:_"
    }

    object FlowBinding {
        const val ANDROID = "io.github.reactivecircus.flowbinding:flowbinding-android:_"
        const val SWIPE_REFRESH_LAYOUT =
            "io.github.reactivecircus.flowbinding:flowbinding-swiperefreshlayout:_"
    }
}
