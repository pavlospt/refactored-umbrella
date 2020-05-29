package com.github.pavlospt.refactoredumbrella.ui.dashboard

import com.github.pavlospt.refactoredumbrella.android.core.adapter.ViewBindingDiffUtilCallback

object VCallback : ViewBindingDiffUtilCallback<VItem>() {
    override fun areItemsTheSame(oldItem: VItem, newItem: VItem): Boolean = true
    override fun areContentsTheSame(oldItem: VItem, newItem: VItem): Boolean = true
}
