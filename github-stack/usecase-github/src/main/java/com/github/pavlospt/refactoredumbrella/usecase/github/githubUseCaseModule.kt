package com.github.pavlospt.refactoredumbrella.usecase.github

import org.koin.dsl.module

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
