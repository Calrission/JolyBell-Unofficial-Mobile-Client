package com.jolybell.jolybellunofficial.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jolybell.jolybellunofficial.databinding.ItemCartBinding
import com.jolybell.jolybellunofficial.models.ModelProduct

class CartAdapter: MutableAdapter<ModelProduct, CartAdapter.VH>() {
    class VH(val binding: ItemCartBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}