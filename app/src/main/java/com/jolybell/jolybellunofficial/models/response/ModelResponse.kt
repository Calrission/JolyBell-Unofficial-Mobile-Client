package com.jolybell.jolybellunofficial.models.response

abstract class ModelResponse {
    abstract val result: Boolean
}

data class ModelResponseData(
    override val result: Boolean
): ModelResponse()