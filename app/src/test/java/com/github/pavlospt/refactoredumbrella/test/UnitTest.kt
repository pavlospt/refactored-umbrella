package com.github.pavlospt.refactoredumbrella.test

import com.github.pavlospt.refactoredumbrella.interactor.AppCoroutineDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Rule

open class UnitTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val testCoroutineDispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
    protected val testAppCoroutineDispatchers: AppCoroutineDispatchers = AppCoroutineDispatchers(
        io = testCoroutineDispatcher,
        computation = testCoroutineDispatcher,
        main = testCoroutineDispatcher
    )
}
