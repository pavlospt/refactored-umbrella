### Creating a new RecyclerView Adapter

* A new `RecyclerView` adapter should extend `ViewBindingAdapter` and implement its methods.

* A new `RecyclerView` adapter should have items that implement `ViewBindingAdapterItem`.

* A new `RecyclerView` adapter should have an `object` implementing `DiffUtil.ItemCallback<T : ViewBindingAdapterItem>`.
