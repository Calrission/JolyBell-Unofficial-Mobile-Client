package com.jolybell.jolybellunofficial.models.request

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
}