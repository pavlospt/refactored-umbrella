package com.github.pavlospt.refactoredumbrella.repo

import com.github.pavlospt.refactoredumbrella.core.dispatchers.AppCoroutineDispatchers
import com.github.pavlospt.refactoredumbrella.core.interactor.NoResultUseCase
import com.github.pavlospt.refactoredumbrella.db.GithubRepoEntity
import com.github.pavlospt.refactoredumbrella.repo.local.GithubLocalRepo
import kotlinx.coroutines.CoroutineDispatcher

class AddRepoUseCase(
    private val appCoroutineDispatchers: AppCoroutineDispatchers,
    private val githubLocalRepo: GithubLocalRepo
) : NoResultUseCase<AddRepoUseCase.Params>() {

    data class Params(
        val repoName: String,
        val repoStars: Int
    )

    override val workDispatcher: CoroutineDispatcher
        get() = appCoroutineDispatchers.io

    override suspend fun run(params: Params) {
        githubLocalRepo.addRepo(
            githubRepoEntity = GithubRepoEntity(
                remoteId = 1234,
                name = params.repoName,
                stars = params.repoStars,
                url = "https://www.google.gr",
                ownerAvatarUrl = "https://lh3.googleusercontent.com/-NEgZ19D7CMk/AAAAAAAAAAI/" +
                        "AAAAAAAAAAA/AKF05nCB5vsIug1l8CWfFWK6t7IQ6Q4EkQ.CMID/s192-c/photo.jpg"
            )
        )
    }
}
