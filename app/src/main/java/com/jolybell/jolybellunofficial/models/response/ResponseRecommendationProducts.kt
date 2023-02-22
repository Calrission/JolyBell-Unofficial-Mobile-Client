package com.jolybell.jolybellunofficial.models.response

import com.jolybell.jolybellunofficial.models.ModelProductShort

class ResponseRecommendationProducts(
    override val result: Boolean,
    val `data`: List<ModelProductShort>,
): ModelResponse()
