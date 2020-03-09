package com.github.pavlospt.refactoredumbrella.interactor

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class ScopedNoResultUseCase<in Params> {

    protected abstract val workScope: CoroutineScope

    abstract suspend fun run(params: Params)

    suspend operator fun invoke(params: Params) = workScope.launch {
        run(params)
    }
}
