package com.jolybell.jolybellunofficial.models.response

import com.jolybell.jolybellunofficial.models.ModelCategories

data class ResponseCategories(
    val `data`: List<ModelCategories>,
    val result: Boolean
)