package com.jolybell.jolybellunofficial.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jolybell.jolybellunofficial.databinding.ItemProductBinding
import com.jolybell.jolybellunofficial.models.ModelProduct
import com.jolybell.jolybellunofficial.сommon.ImageUtils.Companion.setAliasImage
import com.jolybell.jolybellunofficial.сommon.ImageUtils.Companion.setUrlImage
import com.jolybell.jolybellunofficial.сommon.network.Connection
import com.jolybell.jolybellunofficial.сommon.network.HeadersData

class ProductsAdapter: MutableAdapter<ModelProduct, ProductsAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data[position].apply {
            holder.binding.titleProduct.text = name
            holder.binding.priceProduct.text = "$price ${HeadersData.currency}"
            holder.binding.coverProduct.setAliasImage(images[0].alias)
        }
    }
}