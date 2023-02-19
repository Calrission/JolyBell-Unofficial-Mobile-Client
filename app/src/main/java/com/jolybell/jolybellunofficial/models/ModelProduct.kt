package com.jolybell.jolybellunofficial.models

import com.jolybell.jolybellunofficial.dialogs.MessageDialog

data class ModelProduct(
    val id: String,
    val decoration: String,
    override val price: Double,
    val discount: Int,
    val count: Int,
    val created_at: Int,
    val updated_at: Int,
    val name: String,
    val description_main: String,
    val description_sizes: String?,
    val description_care: String?,
    override val sizes: List<ModelSize>,
    val images: List<Image>,
    val category: ModelShortCategory
): ModelPriceSizes() {
    fun getNormalDescription(): String{
        return description_main.replace("/n/n/n/n", "/n/n/n")
    }

    fun getCareModelMessage(): MessageDialog.ModelMessage?{
        val titleMessage = description_care?.substringBefore("\n\n\n\n")?.substringAfter("#### ") ?: return null
        val message = description_care.substringAfter("\n\n\n\n")
        return MessageDialog.ModelMessage(titleMessage, message)
    }

    fun getDescriptionSizesTitle(): String?{
        return description_sizes?.substringBefore(" \n\n![]")
    }

    fun getDescriptionSizesTextTable(): String?{
        val text = description_sizes?.substringAfter("|")?.substringBeforeLast("|")
        return if (text != null) "|$text" else null
    }

    fun getPostfix(): String{
        return if (description_sizes?.contains("*") == true) description_sizes.substringAfter("*").substringBefore("*") else ""
    }

    fun getDescriptionSizesImageUrl(): String?{
        return description_sizes?.substringAfter("[](")?.substringBefore(")")
    }
}

data class ModelShortCategory(
    val id: String,
    val name: String
)