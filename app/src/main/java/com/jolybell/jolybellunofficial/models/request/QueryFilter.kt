package com.jolybell.jolybellunofficial.models.request

data class QueryFilter(
    val `field`: String,
    val reletation: String,
    val type: String,
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
}