package com.github.pavlospt.refactoredumbrella.core.dispatchers

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dispatchersModule = module {
    single {
        AppCoroutineDispatchers(
            io = Dispatchers.IO,
            computation = Dispatchers.Default,
            main = Dispatchers.Main
        )
    }
}
