package com.github.pavlospt.refactoredumbrella.test

import com.github.pavlospt.refactoredumbrella.core.dispatchers.AppCoroutineDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
open class UnitTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val testCoroutineDispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
    protected val testAppCoroutineDispatchers: AppCoroutineDispatchers =
        AppCoroutineDispatchers(
            io = testCoroutineDispatcher,
            computation = testCoroutineDispatcher,
            main = testCoroutineDispatcher
        )
}
