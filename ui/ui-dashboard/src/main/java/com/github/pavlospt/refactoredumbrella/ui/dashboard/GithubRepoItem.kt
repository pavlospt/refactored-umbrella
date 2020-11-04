package com.github.pavlospt.refactoredumbrella.ui.dashboard

data class GithubRepoItem(
    val repoId: Int,
    val name: String,
    val stars: Int,
    val url: String,
    val ownerAvatarUrl: String
)
