package com.github.pavlospt.refactoredumbrella.usecase.github

import com.github.pavlospt.refactoredumbrella.core.dispatchers.AppCoroutineDispatchers
import com.github.pavlospt.refactoredumbrella.core.interactor.FlowUseCase
import com.github.pavlospt.refactoredumbrella.db.github.GithubRepoEntity
import com.github.pavlospt.refactoredumbrella.localrepo.github.GithubLocalRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class ObserveGithubReposUseCase(
    private val appCoroutineDispatchers: AppCoroutineDispatchers,
    private val githubLocalRepo: GithubLocalRepo
) : FlowUseCase<Unit, List<GithubRepoEntity>>() {

    override val dispatcher: CoroutineDispatcher
        get() = appCoroutineDispatchers.io

    override fun doWork(params: Unit): Flow<List<GithubRepoEntity>> =
        githubLocalRepo.observeGithubRepos()
}
