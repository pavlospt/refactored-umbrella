package com.github.pavlospt.refactoredumbrella.ui.dashboard

import com.github.pavlospt.refactoredumbrella.android.core.adapter.ViewBindingDiffUtilCallback

object HCallback : ViewBindingDiffUtilCallback<HItem>() {
    override fun areItemsTheSame(oldItem: HItem, newItem: HItem): Boolean = true
    override fun areContentsTheSame(oldItem: HItem, newItem: HItem): Boolean = true
}
