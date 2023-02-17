package com.jolybell.jolybellunofficial.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jolybell.jolybellunofficial.databinding.ItemProductBinding
import com.jolybell.jolybellunofficial.models.ModelProductShort
import com.jolybell.jolybellunofficial.сommon.utils.ImageUtils.Companion.setAliasImage

class ProductsAdapter(onClick: OnClick<ModelProductShort>): MutableAdapter<ModelProductShort, ProductsAdapter.ViewHolder>(onClick = onClick) {

    class ViewHolder(val binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        data[position].apply {
            holder.binding.productTitle.text = name
            holder.binding.priceProduct.text = getPriceWithCurrency()
            holder.binding.coverProduct.setAliasImage(images[0].alias)
        }
    }
}