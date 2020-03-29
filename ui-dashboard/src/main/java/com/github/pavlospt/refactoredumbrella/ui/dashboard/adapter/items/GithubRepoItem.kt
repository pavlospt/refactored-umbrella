package com.github.pavlospt.refactoredumbrella.ui.dashboard.adapter.items

class GithubRepoItem(
    val repoId: Int,
    val name: String,
    val stars: Int,
    val url: String,
    val ownerAvatarUrl: String
) : RepoItem {
    override val itemViewType: Int
        get() = RepoItem.GITHUB_REPO_VIEW_TYPE
}
