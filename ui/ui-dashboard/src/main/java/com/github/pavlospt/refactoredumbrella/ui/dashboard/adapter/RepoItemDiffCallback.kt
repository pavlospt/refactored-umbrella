package com.github.pavlospt.refactoredumbrella.ui.dashboard.adapter

import com.github.pavlospt.refactoredumbrella.android.core.adapter.ViewBindingDiffUtilCallback
import com.github.pavlospt.refactoredumbrella.ui.dashboard.adapter.items.GithubRepoItem
import com.github.pavlospt.refactoredumbrella.ui.dashboard.adapter.items.RepoItem

object RepoItemDiffCallback : ViewBindingDiffUtilCallback<RepoItem>() {
    override fun areItemsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean {
        if (oldItem !is GithubRepoItem || newItem !is GithubRepoItem) {
            return false
        }

        return oldItem.repoId == newItem.repoId
    }

    override fun areContentsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean {
        if (oldItem !is GithubRepoItem || newItem !is GithubRepoItem) {
            return false
        }

        return oldItem.repoId == newItem.repoId
    }
}
