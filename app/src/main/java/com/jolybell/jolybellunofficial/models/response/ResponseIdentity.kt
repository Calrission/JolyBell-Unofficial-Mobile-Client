package com.jolybell.jolybellunofficial.models.response

import com.jolybell.jolybellunofficial.models.ModelToken
import com.jolybell.jolybellunofficial.models.ModelNotification

class ResponseIdentity(
    val `data`: ModelToken?,
    override val result: Boolean,
    val notification: ModelNotification
): ModelResponse()
