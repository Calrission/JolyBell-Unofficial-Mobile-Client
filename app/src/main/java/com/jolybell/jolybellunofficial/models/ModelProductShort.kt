package com.jolybell.jolybellunofficial.models

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.jolybell.jolybellunofficial.screens.ProductActivity
import com.jolybell.jolybellunofficial.сommon.network.HeadersData
import com.jolybell.jolybellunofficial.сommon.utils.DoubleUtils.Companion.toBeautifulString

data class ModelProductShort(
    val count: Int,
    val created_at: Int,
    val decoration: String,
    val discount: Int,
    val id: String,
    val images: List<Image>,
    val name: String,
    override val price: Double,
    override val sizes: List<ModelSize>,
    val updated_at: Int
): java.io.Serializable, ModelPriceSizes(){
    fun createNewIntentToProductActivity(context: Context): Intent{
        val intent = Intent(context, ProductActivity::class.java)
        val bundle = Bundle()
        bundle.putString("id", id)
        bundle.putString("decoration", decoration)
        intent.putExtras(bundle)
        return intent
    }
}

abstract class ModelPriceSizes: java.io.Serializable {
    abstract val price: Double
    abstract val sizes: List<ModelSize>

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

    fun getPriceWithCurrency(): String{
        return "${getPrice()} ${HeadersData.currency}"
    }
}

data class Image(
    val alias: String,
    val height: Int,
    val id: String,
    val width: Int
): java.io.Serializable

data class ModelSize(
    val id: String,
    val name: String,
    val pivot: Pivot
): java.io.Serializable

data class Pivot(
    val count: Int,
    val price: Double
): java.io.Serializable