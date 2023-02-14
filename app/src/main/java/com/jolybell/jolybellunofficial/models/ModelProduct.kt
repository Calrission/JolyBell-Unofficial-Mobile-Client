package com.jolybell.jolybellunofficial.models

import java.text.DecimalFormat

data class ModelProduct(
    val count: Int,
    val created_at: Int,
    val decoration: String,
    val discount: Int,
    val id: String,
    val images: List<Image>,
    val name: String,
    val price: Double,
    val sizes: List<Size>,
    val updated_at: Int
): java.io.Serializable{
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

    private fun Double.toBeautifulString(): String{
        return DecimalFormat("#0.00").format(this)
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