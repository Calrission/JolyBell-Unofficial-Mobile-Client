package com.jolybell.jolybellunofficial.models.response

import com.jolybell.jolybellunofficial.models.ModelCategory

class ResponseCategories(
    val `data`: List<ModelCategory>,
    override val result: Boolean
): ModelResponse()