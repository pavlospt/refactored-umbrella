package com.github.pavlospt.refactoredumbrella.usecase.github

import com.github.pavlospt.refactoredumbrella.core.dispatchers.AppCoroutineDispatchers
import com.github.pavlospt.refactoredumbrella.core.interactor.FlowUseCase
import com.github.pavlospt.refactoredumbrella.db.github.GithubRepoEntity
import com.github.pavlospt.refactoredumbrella.localrepo.github.GithubLocalRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class ObserveGithubReposUseCase(
    private val appCoroutineDispatchers: AppCoroutineDispatchers,
    private val githubLocalRepo: GithubLocalRepo
) : FlowUseCase<List<GithubRepoEntity>, Unit>() {

    override val dispatcher: CoroutineDispatcher
        get() = appCoroutineDispatchers.io

    override fun doWork(params: Unit): Flow<List<GithubRepoEntity>> =
        githubLocalRepo.observeGithubRepos()
}
