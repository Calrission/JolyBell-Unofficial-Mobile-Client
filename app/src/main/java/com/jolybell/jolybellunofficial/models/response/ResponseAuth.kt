package com.jolybell.jolybellunofficial.models.response

import com.jolybell.jolybellunofficial.models.ModelToken
import com.jolybell.jolybellunofficial.models.ModelNotification

data class ResponseAuth(
    val `data`: ModelToken,
    val modelNotification: ModelNotification,
    val result: Boolean
)