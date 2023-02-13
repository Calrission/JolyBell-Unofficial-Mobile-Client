package com.jolybell.jolybellunofficial.models.request

import com.google.gson.Gson

data class QueryFilter(
    val type: String,
    val reletation: String,
    val `field`: String,
    val value: String
){
    companion object {
        fun createQueryFiltersGetProductsCategory(categoryAlias: String): String{
            return listOf(
                QueryFilter(
                    "and_reletation_field_value_strict", "category",
                    "categories.alias", categoryAlias
                )
            ).toString()
        }
    }


    override fun toString(): String {
        return Gson().toJson(this)
    }
}