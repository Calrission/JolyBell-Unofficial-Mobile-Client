package com.jolybell.jolybellunofficial.models.body

import com.jolybell.jolybellunofficial.сommon.userdata.Cryptography
import com.jolybell.jolybellunofficial.сommon.userdata.Cryptography.Companion.decodeField
import com.jolybell.jolybellunofficial.сommon.userdata.Cryptography.Companion.encodeField

open class IdentityBody(val email: String, val password: String): Cryptography.Crypto<IdentityBody> {

    override fun encode(cryptography: Cryptography): IdentityBody {
        cryptography.apply {
            val newEmail: String = cipherEncrypt.encodeField(email)
            val newPassword: String = cipherEncrypt.encodeField(password)
            return IdentityBody(newEmail, newPassword)
        }
    }

    override fun decode(cryptography: Cryptography): IdentityBody {
        cryptography.apply {
            val newEmail: String = cipherDecrypt.decodeField(email)
            val newPassword: String = cipherDecrypt.decodeField(password)
            return IdentityBody(newEmail, newPassword)
        }
    }
}
