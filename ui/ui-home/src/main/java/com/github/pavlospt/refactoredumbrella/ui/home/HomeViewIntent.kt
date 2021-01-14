package com.github.pavlospt.refactoredumbrella.ui.home

sealed class HomeViewIntent {
    data class AddGithubRepo(val repoName: String, val repoStars: Int) : HomeViewIntent()
    object NotEmitted : HomeViewIntent()
}
