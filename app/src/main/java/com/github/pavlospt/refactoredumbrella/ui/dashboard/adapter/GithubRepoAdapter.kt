package com.github.pavlospt.refactoredumbrella.ui.dashboard.adapter

import android.view.ViewGroup
import coil.transform.CircleCropTransformation
import coil.api.load
import com.github.pavlospt.refactoredumbrella.R
import com.github.pavlospt.refactoredumbrella.adapter.ViewBindingAdapter
import com.github.pavlospt.refactoredumbrella.adapter.ViewBindingViewHolder
import com.github.pavlospt.refactoredumbrella.databinding.ItemGithubRepoBinding
import com.github.pavlospt.refactoredumbrella.ui.dashboard.adapter.items.GithubRepoItem
import com.github.pavlospt.refactoredumbrella.ui.dashboard.adapter.items.RepoItem

class GithubRepoAdapter :
    ViewBindingAdapter<RepoItem, ItemGithubRepoBinding>(RepoItemDiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewBindingViewHolder<RepoItem, ItemGithubRepoBinding> {
        val inflater = parent.layoutInflater
        val binding = ItemGithubRepoBinding.inflate(inflater, parent, false)
        return GithubRepoViewHolder(binding)
    }

    inner class GithubRepoViewHolder(
        binding: ItemGithubRepoBinding
    ) : ViewBindingViewHolder<RepoItem, ItemGithubRepoBinding>(binding) {

        override fun bind(item: RepoItem) {
            item as GithubRepoItem

            binding.repoOwnerAvatar.load(item.ownerAvatarUrl) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
            binding.repoName.text = item.name
            binding.repoStars.text = binding.root.context.getString(R.string.repo_stars, item.stars)
        }
    }
}
