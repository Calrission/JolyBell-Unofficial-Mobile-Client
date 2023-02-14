package com.jolybell.jolybellunofficial.models.response

import com.jolybell.jolybellunofficial.models.ModelProduct

data class ResponseProduct(
    val `data`: ModelProduct,
    override val result: Boolean
): ModelResponse()