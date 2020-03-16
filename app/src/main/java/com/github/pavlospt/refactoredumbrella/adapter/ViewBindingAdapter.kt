package com.github.pavlospt.refactoredumbrella.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

abstract class ViewBindingAdapter<Item : ViewBindingAdapterItem, VB : ViewBinding>(
    diffCallback: DiffUtil.ItemCallback<Item>
) : ListAdapter<Item, ViewBindingViewHolder<Item, VB>>(diffCallback) {
    override fun onBindViewHolder(holder: ViewBindingViewHolder<Item, VB>, position: Int) =
        holder.bind(item = getItem(position))

    override fun onBindViewHolder(
        holder: ViewBindingViewHolder<Item, VB>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        holder.bind(item = getItem(position), payloads = payloads)
    }

    override fun getItemViewType(position: Int): Int = getItem(position).itemViewType

    protected val ViewGroup.layoutInflater: LayoutInflater
        get() = LayoutInflater.from(this.context)
}
