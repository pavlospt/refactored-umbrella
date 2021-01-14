package com.github.pavlospt.refactoredumbrella.ui.home

sealed class HomeUIEvent {
    object None : HomeUIEvent()
    object RepoAdded : HomeUIEvent()
}
