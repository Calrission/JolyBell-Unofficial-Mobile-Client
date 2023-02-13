package com.jolybell.jolybellunofficial.models

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
)

data class Image(
    val alias: String,
    val height: Int,
    val id: String,
    val width: Int
)

data class Size(
    val id: String,
    val name: String,
    val pivot: Pivot
)

data class Pivot(
    val count: Int,
    val price: Double
)