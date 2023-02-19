package com.jolybell.jolybellunofficial.сommon.userdata

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.jolybell.jolybellunofficial.models.ModelToken
import com.jolybell.jolybellunofficial.models.body.LoginBody
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.SecretKeySpec

class CryptographyUserData(
    val context: Context,
) {
    private val algorithm: String = "AES"
    private val algorithmSecureRandom: String = "SHA1PRNG"
    private val secretKeySpec: SecretKeySpec by lazy {
        newSecretKeySpec()
    }
    private val cipherEncrypt: Cipher by lazy {
        newCipher(Cipher.ENCRYPT_MODE)
    }
    private val cipherDecrypt: Cipher by lazy {
        newCipher(Cipher.DECRYPT_MODE)
    }

    fun encodeLoginBody(loginBody: LoginBody): LoginBody{
        val newEmail: String = cipherEncrypt.workUTF8(loginBody.email)
        val newPassword: String = cipherEncrypt.workUTF8(loginBody.password)
        return LoginBody(newEmail, newPassword)
    }

    fun decodeLoginBody(loginBody: LoginBody): LoginBody{
        val newEmail: String = cipherDecrypt.workUTF8(loginBody.email)
        val newPassword: String = cipherDecrypt.workUTF8(loginBody.password)
        return LoginBody(newEmail, newPassword)
    }

    fun encodeModelToken(modelToken: ModelToken): ModelToken{
        val diedAt: String = cipherEncrypt.workUTF8(modelToken.died_at)
        val expiredAt: String = cipherEncrypt.workUTF8(modelToken.expired_at)
        val token: String = cipherEncrypt.workUTF8(modelToken.token)
        return ModelToken(diedAt, expiredAt, token)
    }

    fun decodeModelToken(modelToken: ModelToken): ModelToken{
        val diedAt: String = cipherDecrypt.workUTF8(modelToken.died_at)
        val expiredAt: String = cipherDecrypt.workUTF8(modelToken.expired_at)
        val token: String = cipherDecrypt.workUTF8(modelToken.token)
        return ModelToken(diedAt, expiredAt, token)
    }

    private fun Cipher.workUTF8(field: String): String{
        return String(doFinal(field.toByteArray(Charsets.UTF_8)))
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
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }
}