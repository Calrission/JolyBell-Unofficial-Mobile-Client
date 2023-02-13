package com.jolybell.jolybellunofficial.models.request

import com.google.gson.Gson

data class QuerySort(
    val `field`: String,
    val asc: Boolean,
){
    companion object {
        fun createQuerySortsGetProductsCategory(): String{
            return listOf(
                QuerySort("index", false),
                QuerySort("indexed_at", false),
            ).toString()
        }
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}