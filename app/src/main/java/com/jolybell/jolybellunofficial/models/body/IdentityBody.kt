package com.jolybell.jolybellunofficial.models.body

import com.jolybell.jolybellunofficial.сommon.userdata.Cryptography
import com.jolybell.jolybellunofficial.сommon.userdata.Cryptography.Companion.workUTF8

open class IdentityBody(val email: String, val password: String): Cryptography.Crypto<IdentityBody> {

    override fun encode(cryptography: Cryptography): IdentityBody {
        cryptography.apply {
            val newEmail: String = cipherEncrypt.workUTF8(email)
            val newPassword: String = cipherEncrypt.workUTF8(password)
            return IdentityBody(newEmail, newPassword)
        }
    }

    override fun decode(cryptography: Cryptography): IdentityBody {
        cryptography.apply {
            val newEmail: String = cipherDecrypt.workUTF8(email)
            val newPassword: String = cipherDecrypt.workUTF8(password)
            return IdentityBody(newEmail, newPassword)
        }
    }
}
