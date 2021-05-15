package com.github.pavlospt.refactoredumbrella.ui.home

sealed class HomeViewIntent {
    object None : HomeViewIntent()
    data class AddGithubRepo(val repoName: String, val repoStars: Int) : HomeViewIntent()
}
