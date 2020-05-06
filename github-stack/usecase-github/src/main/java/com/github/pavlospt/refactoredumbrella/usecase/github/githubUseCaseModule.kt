package com.github.pavlospt.refactoredumbrella.usecase.github

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
val githubUseCaseModule = module {
    factory {
        ObserveGithubReposUseCase(
            appCoroutineDispatchers = get(),
            githubLocalRepo = get()
        )
    }

    factory {
        RefreshGithubReposUseCase(
            appCoroutineDispatchers = get(),
            githubRemoteRepo = get(),
            githubLocalRepo = get()
        )
    }

    factory {
        AddRepoUseCase(
            appCoroutineDispatchers = get(),
            githubLocalRepo = get()
        )
    }
}
