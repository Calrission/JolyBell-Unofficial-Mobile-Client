package com.jolybell.jolybellunofficial.models

import com.jolybell.jolybellunofficial.dialogs.MessageDialog

data class ModelNotification(
    val message: String,
    val type: String
){
    fun toModelMessage(): MessageDialog.ModelMessage{
        return MessageDialog.ModelMessage(type, message)
    }
}