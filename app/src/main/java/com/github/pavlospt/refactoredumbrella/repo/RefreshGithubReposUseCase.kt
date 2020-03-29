package com.github.pavlospt.refactoredumbrella.repo

import com.github.pavlospt.refactoredumbrella.core.interactor.NoResultUseCase
import com.github.pavlospt.refactoredumbrella.interactor.AppCoroutineDispatchers
import com.github.pavlospt.refactoredumbrella.repo.local.GithubLocalRepo
import com.github.pavlospt.refactoredumbrella.repo.remote.GithubRemoteRepo
import kotlinx.coroutines.CoroutineDispatcher

class RefreshGithubReposUseCase(
    private val appCoroutineDispatchers: AppCoroutineDispatchers,
    private val githubRemoteRepo: GithubRemoteRepo,
    private val githubLocalRepo: GithubLocalRepo
) : NoResultUseCase<RefreshGithubReposUseCase.Params>() {

    data class Params(val username: String)

    override val workDispatcher: CoroutineDispatcher
        get() = appCoroutineDispatchers.io

    override suspend fun run(params: Params) {
        val remoteRepos: List<GithubRepoModel> =
            githubRemoteRepo.fetchGithubReposFor(username = params.username)
        githubLocalRepo.updateRepos(
            updatedGithubRepoEntities = remoteRepos.map(GithubRepoConverter::convert)
        )
    }
}
