package com.github.pavlospt.refactoredumbrella.repo

import com.github.pavlospt.refactoredumbrella.test.MockGithubLocalRepo
import com.github.pavlospt.refactoredumbrella.test.MockGithubRemoteRepo
import com.github.pavlospt.refactoredumbrella.test.UnitTest
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class RefreshGithubReposUseCaseTest : UnitTest() {

    private val mockGithubLocalRepo = MockGithubLocalRepo()

    @Before
    fun setUp() {
        mockGithubLocalRepo.reset()
    }

    @Test
    fun should_fetch_remote_repos_and_add_them_locally() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val remoteRepos = listOf(
                GithubRepoModel(
                    id = 1,
                    name = "foo",
                    stars = 1,
                    url = "",
                    owner = GithubRepoOwner(
                        avatarUrl = ""
                    )
                ),
                GithubRepoModel(
                    id = 2,
                    name = "bar",
                    stars = 2,
                    url = "",
                    owner = GithubRepoOwner(
                        avatarUrl = ""
                    )
                )
            )
            val githubRemoteRepo = MockGithubRemoteRepo(fetchedRepos = remoteRepos)

            val useCase = RefreshGithubReposUseCase(
                appCoroutineDispatchers = testAppCoroutineDispatchers,
                githubLocalRepo = mockGithubLocalRepo,
                githubRemoteRepo = githubRemoteRepo
            )

            useCase.run(params = RefreshGithubReposUseCase.Params(username = "Foo"))

            mockGithubLocalRepo.updateRepoRenders.assertRenderedOnce()
        }
}
