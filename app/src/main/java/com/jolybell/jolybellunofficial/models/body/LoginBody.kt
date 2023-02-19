package com.jolybell.jolybellunofficial.models.body

import com.jolybell.jolybellunofficial.сommon.userdata.Cryptography
import com.jolybell.jolybellunofficial.сommon.userdata.Cryptography.Companion.workUTF8

data class LoginBody (
    val email: String,
    val password: String
): Cryptography.Crypto<LoginBody>{
    override fun encode(cryptography: Cryptography): LoginBody {
        cryptography.apply {
            val newEmail: String = cipherEncrypt.workUTF8(email)
            val newPassword: String = cipherEncrypt.workUTF8(password)
            return LoginBody(newEmail, newPassword)
        }
    }

    override fun decode(cryptography: Cryptography): LoginBody {
        cryptography.apply {
            val newEmail: String = cipherDecrypt.workUTF8(email)
            val newPassword: String = cipherDecrypt.workUTF8(password)
            return LoginBody(newEmail, newPassword)
        }
    }
}