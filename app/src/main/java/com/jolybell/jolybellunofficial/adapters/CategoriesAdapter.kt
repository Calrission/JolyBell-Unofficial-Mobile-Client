package com.jolybell.jolybellunofficial.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jolybell.jolybellunofficial.databinding.ItemCategoryBinding
import com.jolybell.jolybellunofficial.models.ModelCategory
import com.jolybell.jolybellunofficial.сommon.ImageUtils.Companion.setAsyncPreviewCategory

class CategoriesAdapter(
    private val onClick: OnClick? = null
): MutableAdapter<ModelCategory, CategoriesAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data[position].apply {
            holder.binding.categoryTitle.text = name
            holder.binding.categoryCover.setAsyncPreviewCategory(holder.itemView.context as Activity, alias)
            holder.itemView.setOnClickListener {
                onClick?.onClick(this)
            }
        }
    }

    interface OnClick{
        fun onClick(model: ModelCategory)
    }
}