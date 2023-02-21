package com.jolybell.jolybellunofficial.сommon.userdata

import android.annotation.SuppressLint
import android.content.Context
import android.util.Base64
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.SecretKeySpec

class Cryptography(
    val context: Context,
) {
    private val algorithm: String = "AES"
    private val algorithmSecureRandom: String = "SHA1PRNG"
    private val secretKeySpec: SecretKeySpec by lazy {
        newSecretKeySpec()
    }

    val cipherEncrypt: Cipher by lazy {
        newCipher(Cipher.ENCRYPT_MODE)
    }
    val cipherDecrypt: Cipher by lazy {
        newCipher(Cipher.DECRYPT_MODE)
    }

    companion object {
        fun Cipher.workUTF8(field: String): String{
            val bytes = field.toByteArray(Charsets.UTF_8)
            val newBytes = doFinal(bytes)
            val str = Base64.encodeToString(newBytes, Base64.DEFAULT)
            return str
        }
    }

    private fun newCipher(mode: Int): Cipher {
        return Cipher.getInstance(algorithm).apply {
            init(mode, secretKeySpec)
        }
    }

    private fun newSecretKeySpec(): SecretKeySpec{
        val sr: SecureRandom = SecureRandom.getInstance(algorithmSecureRandom).apply {
            setSeed(getSeed().toByteArray())
        }
        val kg: KeyGenerator = KeyGenerator.getInstance(algorithm).apply {
            init(128, sr)
        }
        return SecretKeySpec(kg.generateKey().encoded, algorithm)
    }

    @SuppressLint("HardwareIds")
    private fun getSeed(): String{
        return "any data used as random seed"
    }

    interface Crypto <T>{
        fun encode(cryptography: Cryptography): T
        fun decode(cryptography: Cryptography): T
    }
}