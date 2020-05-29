package com.github.pavlospt.refactoredumbrella.ui.dashboard

import android.view.ViewGroup
import com.github.pavlospt.refactoredumbrella.android.core.adapter.ViewBindingAdapter
import com.github.pavlospt.refactoredumbrella.android.core.adapter.ViewBindingViewHolder
import com.github.pavlospt.refactoredumbrella.ui.dashboard.databinding.ItemVBinding

class VAdapter : ViewBindingAdapter<VItem, ItemVBinding>(VCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewBindingViewHolder<VItem, ItemVBinding> {
        val inflater = parent.layoutInflater
        return when (viewType) {
            VItem.V_VIEW_TYPE -> {
                val view = ItemVBinding.inflate(inflater, parent, false)
                VViewHolder(view)
            }
            else -> throw IllegalStateException()
        }
    }

    inner class VViewHolder(
        binding: ItemVBinding
    ) : ViewBindingViewHolder<VItem, ItemVBinding>(binding) {

        override fun bind(item: VItem) {
            item as FooVItem

            binding.itemVTv.text = item.title
        }
    }
}
