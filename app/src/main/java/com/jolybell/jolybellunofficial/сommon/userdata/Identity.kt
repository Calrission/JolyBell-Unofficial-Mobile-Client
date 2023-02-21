package com.jolybell.jolybellunofficial.сommon.userdata

import android.content.Context
import android.util.Log
import com.jolybell.jolybellunofficial.models.ModelToken
import com.jolybell.jolybellunofficial.models.body.IdentityBody
import com.jolybell.jolybellunofficial.models.response.ModelUser
import com.jolybell.jolybellunofficial.models.response.ResponseUser
import com.jolybell.jolybellunofficial.сommon.network.Connection
import com.jolybell.jolybellunofficial.сommon.network.ConnectionController
import com.jolybell.jolybellunofficial.сommon.network.ConnectionController.Companion.push

object Identity {
    var isIdentity: Boolean = false
    var token: ModelToken? = null
    set(value) {
        field = value
        isIdentity = true
    }
    var identityBody: IdentityBody? = null
    var user: ModelUser? = null

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

        refreshUser(object: ConnectionController.OnGetData<ModelUser>{
            override fun onGetData(model: ModelUser) {}

            override fun onError(error: String) {
                isIdentity = false
                token = null
                saverUserData.removeModelToken()
            }

        })
    }

    fun refreshUser(onGetData: ConnectionController.OnGetData<ModelUser>? = null){
        Connection.api.getUser().push(object: ConnectionController.OnGetData<ResponseUser>{
            override fun onGetData(model: ResponseUser) {
                user = model.data
                onGetData?.onGetData(user!!)
            }

            override fun onError(error: String) {
                onGetData?.onError(error)
                Log.e("profile-fragment", error)
            }
        })
    }
}