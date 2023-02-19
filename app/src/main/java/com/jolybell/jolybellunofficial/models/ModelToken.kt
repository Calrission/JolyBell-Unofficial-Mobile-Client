package com.jolybell.jolybellunofficial.models

import com.jolybell.jolybellunofficial.сommon.userdata.Cryptography
import com.jolybell.jolybellunofficial.сommon.userdata.Cryptography.Companion.workUTF8

data class ModelToken(
    val died_at: String,
    val expired_at: String,
    val token: String
): Cryptography.Crypto<ModelToken>{
    override fun encode(cryptography: Cryptography): ModelToken {
        cryptography.apply {
            val diedAt: String = cipherEncrypt.workUTF8(died_at)
            val expiredAt: String = cipherEncrypt.workUTF8(expired_at)
            val token: String = cipherEncrypt.workUTF8(token)
            return ModelToken(diedAt, expiredAt, token)
        }
    }

    override fun decode(cryptography: Cryptography): ModelToken {
        cryptography.apply {
            val diedAt: String = cipherDecrypt.workUTF8(died_at)
            val expiredAt: String = cipherDecrypt.workUTF8(expired_at)
            val token: String = cipherDecrypt.workUTF8(token)
            return ModelToken(diedAt, expiredAt, token)
        }
    }

}