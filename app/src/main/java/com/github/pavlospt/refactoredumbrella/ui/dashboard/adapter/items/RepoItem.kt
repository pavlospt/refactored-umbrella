package com.github.pavlospt.refactoredumbrella.ui.dashboard.adapter.items

import com.github.pavlospt.refactoredumbrella.android.core.adapter.ViewBindingAdapterItem

interface RepoItem : ViewBindingAdapterItem {
    companion object {
        const val GITHUB_REPO_VIEW_TYPE = 1
    }
}
