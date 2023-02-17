package com.jolybell.jolybellunofficial.adapters

import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jolybell.jolybellunofficial.databinding.ItemRecommendationBinding
import com.jolybell.jolybellunofficial.models.ModelProductShort
import com.jolybell.jolybellunofficial.сommon.utils.ImageUtils.Companion.setAliasImage

class RecommendationsAdapter(
    private val theme: Int,
    onClick: OnClick<ModelProductShort>
): MutableAdapter<ModelProductShort, RecommendationsAdapter.ViewHolder>(onClick = onClick) {

    class ViewHolder(val binding: ItemRecommendationBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRecommendationBinding.inflate(
            LayoutInflater.from(parent.context).cloneInContext(ContextThemeWrapper(parent.context, theme))
            , parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        data[position].apply {
            holder.binding.apply {
                coverRecomm.setAliasImage(images[0].alias)
                titleRecomm.text = name
                priceRecomm.text = getPriceWithCurrency()
            }
        }


    }
}