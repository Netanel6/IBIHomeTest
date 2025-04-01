package com.netanel.ibihometest.products.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.netanel.ibihometest.databinding.ProductItemBinding
import com.netanel.ibihometest.products.data.model.Product

class ProductListAdapter(
    private val onItemClick: (Product) -> Unit
) : ListAdapter<Product, ProductListAdapter.ProductViewHolder>(DiffCallback) {

    inner class ProductViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) = with(binding) {
            textTitle.text = product.title
            textPrice.text = "₪${product.price}"
            textDescription.text = product.description

            Glide.with(imageThumbnail)
                .load(product.thumbnail)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(imageThumbnail)

            root.setOnClickListener {
                onItemClick(product)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(old: Product, new: Product) = old.id == new.id
        override fun areContentsTheSame(old: Product, new: Product) = old == new
    }
}
