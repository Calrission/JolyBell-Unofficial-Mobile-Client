package com.jolybell.jolybellunofficial.models.response

import com.jolybell.jolybellunofficial.models.ModelNotification

open class ResponseNotification(
    override val result: Boolean,
    val notification: ModelNotification
): ModelResponse()