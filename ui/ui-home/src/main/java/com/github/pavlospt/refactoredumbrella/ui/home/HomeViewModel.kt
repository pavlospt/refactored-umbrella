package com.github.pavlospt.refactoredumbrella.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.pavlospt.refactoredumbrella.usecase.github.AddRepoUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class HomeViewModel(private val addRepoUseCase: AddRepoUseCase) : ViewModel() {

    private val _intentChannel = MutableStateFlow<HomeViewIntent>(HomeViewIntent.NotEmitted)

    private val _uiEvents: MutableLiveData<HomeUIEvent> = MutableLiveData()
    val uiEvents: LiveData<HomeUIEvent>
        get() = _uiEvents

    init {
        _intentChannel
            .asStateFlow()
            .onEach { viewIntent ->
                when (viewIntent) {
                    is HomeViewIntent.AddGithubRepo -> addGithubRepo(githubRepo = viewIntent)
                    else -> Unit
                }
            }
            .launchIn(viewModelScope)
    }

    fun processIntent(intentHome: HomeViewIntent) {
        _intentChannel.value = intentHome
    }

    private suspend fun addGithubRepo(githubRepo: HomeViewIntent.AddGithubRepo) {
        if (githubRepo.repoName.isBlank()) {
            return
        }

        addRepoUseCase(
            AddRepoUseCase.Params(
                repoName = githubRepo.repoName,
                repoStars = githubRepo.repoStars
            )
        )

        _uiEvents.value = HomeUIEvent.RepoAdded
    }
}
