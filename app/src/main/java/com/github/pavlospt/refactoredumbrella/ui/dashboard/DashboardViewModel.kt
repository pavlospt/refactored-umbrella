package com.github.pavlospt.refactoredumbrella.ui.dashboard

import androidx.lifecycle.*
import com.github.pavlospt.refactoredumbrella.repo.ObserveGithubReposUseCase
import com.github.pavlospt.refactoredumbrella.repo.RefreshGithubReposUseCase
import com.github.pavlospt.refactoredumbrella.ui.dashboard.adapter.items.GithubRepoItem
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val refreshGithubReposUseCase: RefreshGithubReposUseCase,
    observeGithubReposUseCase: ObserveGithubReposUseCase
) : ViewModel() {

    private val _githubRepos = observeGithubReposUseCase
        .observe()
        .map { reposList ->
            reposList
                .sortedByDescending { it.stars }
                .map {
                    GithubRepoItem(
                        repoId = it.remoteId,
                        name = it.name,
                        stars = it.stars,
                        url = it.url,
                        ownerAvatarUrl = it.ownerAvatarUrl
                    )
                }
        }
        .asLiveData(viewModelScope.coroutineContext)

    val githubRepos: LiveData<List<GithubRepoItem>>
        get() = _githubRepos

    private val _intentChannel = ConflatedBroadcastChannel<ViewIntent>()

    init {
        _intentChannel
            .asFlow()
            .onEach { viewIntent ->
                when (viewIntent) {
                    ViewIntent.Refresh -> refreshRepos()
                }
            }
            .launchIn(viewModelScope)

        viewModelScope.launch { refreshRepos() }

        observeGithubReposUseCase(Unit)
    }

    suspend fun processIntent(intent: ViewIntent) = _intentChannel.send(intent)

    private suspend fun refreshRepos() {
        refreshGithubReposUseCase(RefreshGithubReposUseCase.Params(username = "pavlospt"))
    }
}
