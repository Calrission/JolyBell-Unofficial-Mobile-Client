package com.jolybell.jolybellunofficial.models.response

class ResponseUser(
    val `data`: ModelUser,
    override val result: Boolean
): ModelResponse()
data class ModelUser(
    var id: String,
    var first_name: String?,
    var last_name: String?,
    var middle_name: String?,
    var email: String,
    var phone_number: String?,
    var address: String?,
    var country: ModelCountry?,
    var region: String?,
    var city: String?,
    var zip_code: String?,
    var role: String?,
    var socialized: Boolean,
    var updated_at: Int,
    var created_at: Int,
){
    fun copy(): ModelUser{
        return ModelUser(
            id, first_name, last_name, middle_name, email, phone_number, address,
            country, region, city, zip_code, role, socialized, updated_at, created_at
        )
    }

    fun toUpdateModelUser(): UpdateModelUser{
        return UpdateModelUser(
            first_name, last_name, middle_name, email, phone_number, address,
            city, region, city, zip_code
        )
    }
}

data class UpdateModelUser(
    var first_name: String?,
    var last_name: String?,
    var middle_name: String?,
    var email: String,
    var phone_number: String?,
    var address: String?,
    var country: String?,
    var region: String?,
    var city: String?,
    var zip_code: String?,
)

data class ModelCountry(
    val id: String,
    val name: String
)