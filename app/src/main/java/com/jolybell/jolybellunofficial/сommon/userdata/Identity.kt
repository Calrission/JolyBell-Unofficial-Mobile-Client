package com.jolybell.jolybellunofficial.сommon.userdata

import android.content.Context
import com.jolybell.jolybellunofficial.models.ModelToken
import com.jolybell.jolybellunofficial.models.body.LoginBody

object Identity {
    var isIdentity: Boolean = false
    var token: ModelToken? = null
    var loginBody: LoginBody? = null

    fun load(context: Context){
        val saverUserData = SaverUserData(context)
        val cryptographyUserData = Cryptography(context)

        isIdentity = saverUserData.isExist()

        saverUserData.loadModelToken().apply {
            if (this != null)
                Identity.token = decode(cryptographyUserData)
        }

        saverUserData.loadLoginBody().apply {
            if (this != null)
                loginBody = decode(cryptographyUserData)
        }
    }
}