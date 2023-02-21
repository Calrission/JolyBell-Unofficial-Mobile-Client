package com.jolybell.jolybellunofficial.сommon.userdata

import android.content.Context
import com.jolybell.jolybellunofficial.models.ModelToken
import com.jolybell.jolybellunofficial.models.body.IdentityBody

object Identity {
    var isIdentity: Boolean = false
    var token: ModelToken? = null
    set(value) {
        field = value
        isIdentity = true
    }
    var identityBody: IdentityBody? = null

    fun load(context: Context){
        val saverUserData = SaverUserData(context)
        val cryptographyUserData = Cryptography(context)

        isIdentity = saverUserData.isExist()

        saverUserData.loadModelToken().apply {
            if (this != null)
//                Identity.token = decode(cryptographyUserData)
                Identity.token = this
        }

        saverUserData.loadIdentityBody().apply {
            if (this != null)
//                identityBody = decode(cryptographyUserData)
                identityBody = this
        }
    }
}