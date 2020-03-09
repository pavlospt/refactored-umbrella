package com.github.pavlospt.refactoredumbrella.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.pavlospt.refactoredumbrella.repo.ObserveGithubReposUseCase
import com.github.pavlospt.refactoredumbrella.repo.RefreshGithubReposUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val refreshGithubReposUseCase: RefreshGithubReposUseCase,
    private val observeGithubReposUseCase: ObserveGithubReposUseCase
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }

    val text: LiveData<String> = _text

    init {
        viewModelScope.launch {
            refreshGithubReposUseCase(RefreshGithubReposUseCase.Params(username = "pavlospt"))
        }

        viewModelScope.launch {
            observeGithubReposUseCase.observe().collect {
                it.forEach { repo -> println(repo.name) }
            }
        }

        observeGithubReposUseCase(Unit)
    }
}
