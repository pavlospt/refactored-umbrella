package com.github.pavlospt.refactoredumbrella.android.core.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class ViewBindingViewHolder<Item : ViewBindingAdapterItem, out VB : ViewBinding>(
    protected val binding: VB
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: Item)
    open fun bind(item: Item, payloads: List<Any>) {
        if (payloads.isEmpty()) {
            bind(item = item)
        }
    }
}
