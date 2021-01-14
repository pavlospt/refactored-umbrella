package com.github.pavlospt.refactoredumbrella.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.github.pavlospt.refactoredumbrella.usecase.github.ObserveGithubReposUseCase
import com.github.pavlospt.refactoredumbrella.usecase.github.RefreshGithubReposUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
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

    private val _intentChannel = MutableStateFlow<DashboardViewIntent>(DashboardViewIntent.None)

    init {
        _intentChannel
            .asStateFlow()
            .onEach { viewIntent ->
                when (viewIntent) {
                    DashboardViewIntent.Refresh -> refreshRepos()
                    else -> Unit
                }
            }
            .launchIn(viewModelScope)

        observeGithubReposUseCase(Unit)
    }

    fun processIntent(intentDashboard: DashboardViewIntent) {
        _intentChannel.value = intentDashboard
    }

    private suspend fun refreshRepos() {
        refreshGithubReposUseCase(RefreshGithubReposUseCase.Params(username = "pavlospt"))
    }
}
