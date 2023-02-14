package com.jolybell.jolybellunofficial.adapters

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class MutableAdapter <T, R: ViewHolder> (
    protected val data: MutableList<T> = mutableListOf(),
    var onClick: OnClick<T>? = null
): RecyclerView.Adapter<R>() {

    override fun getItemCount(): Int = data.size

    fun setData(data: Collection<T>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: R, position: Int) {
        holder.itemView.setOnClickListener {
            onClick?.onClick(data[position])
        }
    }

    interface OnClick <T>{
        fun onClick(model: T)
    }
}