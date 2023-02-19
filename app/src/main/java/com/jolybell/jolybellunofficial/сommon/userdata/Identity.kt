package com.jolybell.jolybellunofficial.сommon.userdata

import android.content.Context
import com.jolybell.jolybellunofficial.models.ModelToken

object Identity {
    var isIdentity: Boolean = false
    var token: ModelToken? = null

    fun load(context: Context){
        val saverUserData = SaverUserData(context)
        val cryptographyUserData = CryptographyUserData(context)

        isIdentity = saverUserData.isExist()

        val cryptModelToken = saverUserData.loadModelToken() ?: return
        token = cryptographyUserData.decodeModelToken(cryptModelToken)
    }
}