object TestDeps {
    object JUnit {
        private const val VERSION = "4.12"
        const val JUNIT = "junit:junit:$VERSION"
    }

    object Kotlinx {
        private const val VERSION = SharedVersions.Kotlinx.COROUTINES
        const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$VERSION"
    }

    object AndroidX {
        object Arch {
            const val CORE_TESTING =
                "androidx.arch.core:core-testing:${SharedVersions.AndroidX.Arch.CORE}"
        }
    }

    object CashApp {
        object Turbine {
            private const val VERSION = "0.5.0"
            const val TURBINE = "app.cash.turbine:turbine:$VERSION"
        }
    }
}
