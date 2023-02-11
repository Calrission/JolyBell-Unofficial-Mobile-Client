package com.jolybell.jolybellunofficial.models.body

import com.google.gson.annotations.SerializedName

data class RegisterBody(
    val email: String,
    val password: String,
    @SerializedName("password_confirmation")
    val confirmPassword: String
)
