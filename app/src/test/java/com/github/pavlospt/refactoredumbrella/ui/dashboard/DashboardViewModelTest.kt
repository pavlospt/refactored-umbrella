package com.github.pavlospt.refactoredumbrella.ui.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.pavlospt.refactoredumbrella.remoterepo.github.GithubRepoModel
import com.github.pavlospt.refactoredumbrella.remoterepo.github.GithubRepoOwner
import com.github.pavlospt.refactoredumbrella.test.MockGithubLocalRepo
import com.github.pavlospt.refactoredumbrella.test.MockGithubRemoteRepo
import com.github.pavlospt.refactoredumbrella.test.UnitTest
import com.github.pavlospt.refactoredumbrella.test.forceGet
import com.github.pavlospt.refactoredumbrella.test.observeForTesting
import com.github.pavlospt.refactoredumbrella.usecase.github.ObserveGithubReposUseCase
import com.github.pavlospt.refactoredumbrella.usecase.github.RefreshGithubReposUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DashboardViewModelTest : UnitTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun test_repo_refresh() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val remoteRepos = listOf(
            GithubRepoModel(
                id = 1,
                name = "foo",
                stars = 123,
                url = "",
                owner = GithubRepoOwner(
                    avatarUrl = ""
                )
            ),
            GithubRepoModel(
                id = 2,
                name = "bar",
                stars = 432,
                url = "",
                owner = GithubRepoOwner(
                    avatarUrl = ""
                )
            )
        )
        val mockGithubRemoteRepo = MockGithubRemoteRepo(fetchedRepos = remoteRepos)
        val mockGithubLocalRepo = MockGithubLocalRepo(
            observedGithubRepos = listOf(
                com.github.pavlospt.refactoredumbrella.db.github.GithubRepoEntity(
                    internalId = null,
                    remoteId = 1,
                    name = "foo",
                    stars = 123,
                    url = "",
                    ownerAvatarUrl = ""
                ),
                com.github.pavlospt.refactoredumbrella.db.github.GithubRepoEntity(
                    internalId = null,
                    remoteId = 2,
                    name = "bar",
                    stars = 432,
                    url = "",
                    ownerAvatarUrl = ""
                )
            )
        )

        val refreshGithubRepoUseCase = RefreshGithubReposUseCase(
            appCoroutineDispatchers = testAppCoroutineDispatchers,
            githubRemoteRepo = mockGithubRemoteRepo,
            githubLocalRepo = mockGithubLocalRepo
        )

        val observeGithubReposUseCase = ObserveGithubReposUseCase(
            appCoroutineDispatchers = testAppCoroutineDispatchers,
            githubLocalRepo = mockGithubLocalRepo
        )

        val vm =
            DashboardViewModel(
                refreshGithubReposUseCase = refreshGithubRepoUseCase,
                observeGithubReposUseCase = observeGithubReposUseCase
            )

        vm.processIntent(intentDashboard = DashboardViewIntent.Refresh)

        vm.githubRepos.observeForTesting {
            val (firstRepo, secondRepo) = vm.githubRepos.forceGet

            assertEquals(2, firstRepo.repoId)
            assertEquals(1, secondRepo.repoId)
        }
    }
}
