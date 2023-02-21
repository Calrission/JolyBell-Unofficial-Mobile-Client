package com.jolybell.jolybellunofficial.models

import com.jolybell.jolybellunofficial.сommon.userdata.Cryptography
import com.jolybell.jolybellunofficial.сommon.userdata.Cryptography.Companion.decodeField
import com.jolybell.jolybellunofficial.сommon.userdata.Cryptography.Companion.encodeField
import java.util.Date

data class ModelToken(
    val died_at: String,
    val expired_at: String,
    val token: String
): Cryptography.Crypto<ModelToken>{
    override fun encode(cryptography: Cryptography): ModelToken {
        cryptography.apply {
            val diedAt: String = cipherEncrypt.encodeField(died_at)
            val expiredAt: String = cipherEncrypt.encodeField(expired_at)
            val token: String = cipherEncrypt.encodeField(token)
            return ModelToken(diedAt, expiredAt, token)
        }
    }

    override fun decode(cryptography: Cryptography): ModelToken {
        cryptography.apply {
            val diedAt: String = cipherDecrypt.decodeField(died_at)
            val expiredAt: String = cipherDecrypt.decodeField(expired_at)
            val token: String = cipherDecrypt.decodeField(token)
            return ModelToken(diedAt, expiredAt, token)
        }
    }

    fun checkLiquidityToken(): Boolean{
        return expired_at.toInt() > Date().time
    }
}