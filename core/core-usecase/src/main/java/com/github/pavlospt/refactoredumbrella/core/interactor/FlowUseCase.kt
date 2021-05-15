package com.github.pavlospt.refactoredumbrella.core.interactor

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class, ObsoleteCoroutinesApi::class)
abstract class FlowUseCase<Type : Any, Params : Any> : ObservableUseCase<Type> {

    private val paramState = MutableSharedFlow<Params>(
        replay = 1,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    operator fun invoke(params: Params) {
        paramState.tryEmit(params)
    }

    protected abstract fun doWork(params: Params): Flow<Type>

    fun produce(params: Params): Flow<Type> = doWork(params = params)
        .flowOn(dispatcher)

    override fun observe(): Flow<Type> = paramState
        .flatMapLatest { doWork(it) }
        .flowOn(dispatcher)
}
