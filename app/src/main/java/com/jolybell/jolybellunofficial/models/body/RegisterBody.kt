package com.jolybell.jolybellunofficial.models.body

import com.google.gson.annotations.SerializedName

class RegisterBody(
    email: String,
    password: String,
    @SerializedName("password_confirmation")
    val confirmPassword: String
): IdentityBody(email, password) {
    fun toLoginBody(): LoginBody = LoginBody(email, password)

    companion object {
        fun IdentityBody.toRegisterBody(): RegisterBody = RegisterBody(email, password, password)
    }
}
