package com.jolybell.jolybellunofficial.models

import com.jolybell.jolybellunofficial.сommon.DoubleUtils.Companion.toBeautifulString

data class ModelProductCategory(
    val count: Int,
    val created_at: Int,
    val decoration: String,
    val discount: Int,
    val id: String,
    val images: List<Image>,
    val name: String,
    override val price: Double,
    override val sizes: List<Size>,
    val updated_at: Int
): java.io.Serializable, ModelPriceSizes()

abstract class ModelPriceSizes: java.io.Serializable {
    abstract val price: Double
    abstract val sizes: List<Size>

    fun getPrice(): String{
        if (price != 0.0){
            return price.toBeautifulString()
        }else{
            val prices = sizes.map { it.pivot.price }
            val min = prices.min()
            val max = prices.max()
            if (min == max)
                return min.toBeautifulString()
            return "${min.toBeautifulString()} - ${max.toBeautifulString()}"
        }
    }
}

data class Image(
    val alias: String,
    val height: Int,
    val id: String,
    val width: Int
): java.io.Serializable

data class Size(
    val id: String,
    val name: String,
    val pivot: Pivot
): java.io.Serializable

data class Pivot(
    val count: Int,
    val price: Double
): java.io.Serializable