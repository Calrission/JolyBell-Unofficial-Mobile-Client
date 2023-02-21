package com.jolybell.jolybellunofficial.models.body

class LoginBody (
    email: String,
    password: String
): IdentityBody(email, password) {
    fun toRegisterBody(): RegisterBody = RegisterBody(email, password, password)

    companion object {
        fun IdentityBody.toLoginBody(): LoginBody{
            return LoginBody(email, password)
        }
    }
}