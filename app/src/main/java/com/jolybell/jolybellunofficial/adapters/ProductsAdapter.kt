package com.jolybell.jolybellunofficial.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jolybell.jolybellunofficial.databinding.ItemProductBinding
import com.jolybell.jolybellunofficial.models.ModelProductCategory
import com.jolybell.jolybellunofficial.сommon.utils.ImageUtils.Companion.setAliasImage
import com.jolybell.jolybellunofficial.сommon.network.HeadersData

class ProductsAdapter(onClick: OnClick<ModelProductCategory>): MutableAdapter<ModelProductCategory, ProductsAdapter.ViewHolder>(onClick = onClick) {

    class ViewHolder(val binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        data[position].apply {
            holder.binding.productTitle.text = name
            holder.binding.priceProduct.text = "${getPrice()} ${HeadersData.currency}"
            holder.binding.coverProduct.setAliasImage(images[0].alias)
        }
    }
}