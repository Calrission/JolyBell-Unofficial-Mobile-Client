package com.jolybell.jolybellunofficial.models.response

data class ResponseUser(
    val `data`: DataUser,
    val result: Boolean
)
data class DataUser(
    val address: Any,
    val city: Any,
    val country: Any,
    val created_at: Int,
    val email: String,
    val email_verified_at: Any,
    val first_name: Any,
    val id: String,
    val last_name: Any,
    val middle_name: Any,
    val phone_number: Any,
    val region: Any,
    val role: Any,
    val socialites: List<Any>,
    val socialized: Boolean,
    val updated_at: Int,
    val zip_code: Any
)
