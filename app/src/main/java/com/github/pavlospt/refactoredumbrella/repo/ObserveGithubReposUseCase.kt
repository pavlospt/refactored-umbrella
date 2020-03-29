package com.github.pavlospt.refactoredumbrella.repo

import com.github.pavlospt.refactoredumbrella.core.dispatchers.AppCoroutineDispatchers
import com.github.pavlospt.refactoredumbrella.core.interactor.FlowUseCase
import com.github.pavlospt.refactoredumbrella.db.GithubRepoEntity
import com.github.pavlospt.refactoredumbrella.repo.local.GithubLocalRepo
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
