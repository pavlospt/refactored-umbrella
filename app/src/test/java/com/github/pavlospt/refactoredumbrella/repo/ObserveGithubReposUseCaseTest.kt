package com.github.pavlospt.refactoredumbrella.repo

import com.github.pavlospt.refactoredumbrella.db.GithubRepoEntity
import com.github.pavlospt.refactoredumbrella.test.MockGithubLocalRepo
import com.github.pavlospt.refactoredumbrella.test.UnitTest
import com.github.pavlospt.refactoredumbrella.test.test
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ObserveGithubReposUseCaseTest : UnitTest() {

    @Test
    fun test_should_return_repos_from_local_repo() = runBlockingTest {
        val localRepos = listOf(
            GithubRepoEntity(
                internalId = null,
                remoteId = 1,
                name = "foo",
                stars = 123,
                url = "",
                ownerAvatarUrl = ""
            ),
            GithubRepoEntity(
                internalId = null,
                remoteId = 2,
                name = "bar",
                stars = 432,
                url = "",
                ownerAvatarUrl = ""
            )
        )
        val mockGithubLocalRepo = MockGithubLocalRepo(observedGithubRepos = localRepos)

        val useCase = ObserveGithubReposUseCase(
            appCoroutineDispatchers = testAppCoroutineDispatchers,
            githubLocalRepo = mockGithubLocalRepo
        )

        useCase(Unit)

        launch {
            useCase.observe().test { assertEquals(localRepos, expectItem()) }
        }
    }
}
