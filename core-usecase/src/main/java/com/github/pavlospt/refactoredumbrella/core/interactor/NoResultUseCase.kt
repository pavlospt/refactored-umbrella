package com.github.pavlospt.refactoredumbrella.core.interactor

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class NoResultUseCase<in Params> {

    protected abstract val workDispatcher: CoroutineDispatcher

    abstract suspend fun run(params: Params)

    suspend operator fun invoke(params: Params) = withContext(workDispatcher) {
        run(params)
    }
}
