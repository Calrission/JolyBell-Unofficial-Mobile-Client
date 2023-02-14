package com.jolybell.jolybellunofficial.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jolybell.jolybellunofficial.databinding.ItemImageBinding
import com.jolybell.jolybellunofficial.models.Image
import com.jolybell.jolybellunofficial.сommon.ImageUtils.Companion.setAliasImage

class ImagesAdapter: MutableAdapter<Image, ImagesAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemImageBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.binding.imageProduct.setAliasImage(data[position].alias)
    }
}