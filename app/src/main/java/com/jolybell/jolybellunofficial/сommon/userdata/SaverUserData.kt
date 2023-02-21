package com.jolybell.jolybellunofficial.сommon.userdata

import android.content.Context
import com.jolybell.jolybellunofficial.models.ModelToken
import com.jolybell.jolybellunofficial.models.body.IdentityBody
import com.jolybell.jolybellunofficial.models.body.LoginBody

class SaverUserData(val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("user-data-saver", Context.MODE_PRIVATE)

    fun saveIdentityBody(identityBody: IdentityBody){
        sharedPreferences.edit()
            .putString("email", identityBody.email)
            .putString("password", identityBody.password)
            .apply()
    }

    fun removeIdentityBody(){
        sharedPreferences.edit()
            .remove("email")
            .remove("password")
            .apply ()
    }

    fun loadIdentityBody(): IdentityBody?{
        if (!isExistLoginBody())
            return null
        return IdentityBody(
            sharedPreferences.getString("email", "")!!,
            sharedPreferences.getString("password", "")!!
        )
    }

    fun saveModelToken(modelToken: ModelToken){
        sharedPreferences.edit()
            .putString("died_at", modelToken.died_at)
            .putString("token", modelToken.token)
            .putString("expired_at", modelToken.expired_at)
            .apply()
    }

    fun removeModelToken(){
        sharedPreferences.edit()
            .remove("died_at")
            .remove("token")
            .remove("expired_at")
            .apply()
    }

    fun loadModelToken(): ModelToken?{
        if (!isExistToken())
            return null
        return ModelToken(
            sharedPreferences.getString("died_at", "")!!,
            sharedPreferences.getString("expired_at", "")!!,
            sharedPreferences.getString("token", "")!!
        )
    }

    fun isExistLoginBody(): Boolean{
        return sharedPreferences.let {
            it.contains("email") && it.contains("password")
        }
    }

    fun isExistToken(): Boolean{
        return sharedPreferences.let {
            it.contains("died_at") && it.contains("expired_at") && it.contains("token")
        }
    }

    fun isExist(): Boolean = isExistLoginBody() && isExistToken()

    fun remove(){
        removeModelToken()
        removeIdentityBody()
    }
}