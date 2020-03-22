package com.github.pavlospt.refactoredumbrella.test

import androidx.lifecycle.LiveData
import com.github.pavlospt.refactoredumbrella.interactor.AppCoroutineDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher

open class UnitTest {
    private val testCoroutineDispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
    protected val testAppCoroutineDispatchers: AppCoroutineDispatchers = AppCoroutineDispatchers(
        io = testCoroutineDispatcher,
        computation = testCoroutineDispatcher,
        main = testCoroutineDispatcher
    )

    protected val <T> LiveData<T>.get: T
        get() = this.value!!
}
