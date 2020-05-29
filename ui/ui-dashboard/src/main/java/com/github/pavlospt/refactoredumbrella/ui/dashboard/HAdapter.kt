package com.github.pavlospt.refactoredumbrella.ui.dashboard

import android.view.ViewGroup
import com.github.pavlospt.refactoredumbrella.android.core.adapter.ViewBindingAdapter
import com.github.pavlospt.refactoredumbrella.android.core.adapter.ViewBindingViewHolder
import com.github.pavlospt.refactoredumbrella.ui.dashboard.databinding.ItemHBinding
import java.lang.IllegalStateException

class HAdapter : ViewBindingAdapter<HItem, ItemHBinding>(HCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewBindingViewHolder<HItem, ItemHBinding> {
        val inflater = parent.layoutInflater
        return when (viewType) {
            HItem.H_VIEW_TYPE -> {
                val view = ItemHBinding.inflate(inflater, parent, false)
                HViewHolder(view)
            }
            else -> throw IllegalStateException()
        }
    }

    inner class HViewHolder(
        binding: ItemHBinding
    ) : ViewBindingViewHolder<HItem, ItemHBinding>(binding) {

        override fun bind(item: HItem) {
            item as FooHItem

            binding.itemHTv.text = item.title
        }
    }
}
