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
        val titleMessage = description_care?.replace("\r", "\n")?.substringAfter("#### ")?.substringBefore("\n") ?: return null
        val message = description_care.replace("\r", "\n").replace("\n\n\n\n", "\n").substringAfter(titleMessage).trim()
        return MessageDialog.ModelMessage(titleMessage, message)
    }

    fun getTitle(): String{
        if (description_sizes == null)
            return ""

        val start = description_sizes.substringBefore("![").substringBefore("|").trim()
        val end = description_sizes.substringAfterLast("|").substringAfterLast("*").replace(getTextImage(), "").trim()
        return start + end
    }

    fun getUrlImage(): String{
        if (description_sizes == null || "](" !in description_sizes)
            return ""

        return description_sizes.substringAfter("](").substringBefore(")")
    }

    private fun getPrefixImage(): String{
        if (description_sizes == null)
            return ""

        return "![" + description_sizes.substringAfter("[").substringBefore("]") + "]"
    }

    private fun getTextImage(): String{
        val url = getUrlImage()
        if (description_sizes == null && url.isBlank())
            return ""
        return "${getPrefixImage()}(${url})"
    }

    fun getTextTable(): String{
        if (description_sizes == null)
            return ""

        return "|" + description_sizes.substringAfter("|").substringBeforeLast("|")
    }

     fun getPostfix(): String{
        return if (description_sizes != null && description_sizes.contains("*"))
            description_sizes.substringAfter("*").substringBefore("*")
        else ""
    }
}

data class ModelShortCategory(
    val id: String,
    val name: String
)