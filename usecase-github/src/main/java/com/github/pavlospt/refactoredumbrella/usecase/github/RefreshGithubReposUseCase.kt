package com.github.pavlospt.refactoredumbrella.usecase.github

import com.github.pavlospt.refactoredumbrella.core.dispatchers.AppCoroutineDispatchers
import com.github.pavlospt.refactoredumbrella.core.interactor.NoResultUseCase
import com.github.pavlospt.refactoredumbrella.localrepo.github.GithubLocalRepo
import com.github.pavlospt.refactoredumbrella.remoterepo.github.GithubRemoteRepo
import com.github.pavlospt.refactoredumbrella.remoterepo.github.GithubRepoModel
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
