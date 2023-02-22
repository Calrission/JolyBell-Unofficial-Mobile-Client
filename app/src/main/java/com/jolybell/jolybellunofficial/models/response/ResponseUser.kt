package com.jolybell.jolybellunofficial.models.response

data class ResponseUser(
    val `data`: ModelUser,
    override val result: Boolean
): ModelResponse()
data class ModelUser(
    val address: String?,
    val city: String?,
    val country: ModelCountry?,
    val created_at: Int,
    val email: String,
    val first_name: String?,
    val id: String,
    val last_name: String?,
    val middle_name: String?,
    val phone_number: String?,
    val region: String?,
    val role: String?,
    val socialized: Boolean,
    val updated_at: Int,
    val zip_code: String?
)

data class ModelCountry(
    val id: String,
    val name: String
)