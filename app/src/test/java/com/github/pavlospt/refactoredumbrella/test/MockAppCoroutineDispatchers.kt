package com.github.pavlospt.refactoredumbrella.test

import com.github.pavlospt.refactoredumbrella.interactor.AppCoroutineDispatchers
import kotlinx.coroutines.Dispatchers

val testAppCoroutineDispatchers = AppCoroutineDispatchers(
    io = Dispatchers.Unconfined,
    computation = Dispatchers.Unconfined,
    main = Dispatchers.Unconfined
)
