package com.jolybell.jolybellunofficial.models.response

import com.jolybell.jolybellunofficial.models.ModelToken
import com.jolybell.jolybellunofficial.models.ModelNotification

data class ResponseIdentity(
    val `data`: ModelToken?,
    val notification: ModelNotification,
    override val result: Boolean
): ModelResponse()