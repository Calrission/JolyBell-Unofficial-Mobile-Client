package com.jolybell.jolybellunofficial.models.response

import com.jolybell.jolybellunofficial.models.ModelCategory

data class ResponseCategories(
    val `data`: List<ModelCategory>,
    val result: Boolean
)