package com.jolybell.jolybellunofficial.сommon.userdata

import android.content.Context
import com.jolybell.jolybellunofficial.models.ModelToken
import com.jolybell.jolybellunofficial.models.body.LoginBody

class SaverUserData(val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("user-data-saver", Context.MODE_PRIVATE)

    fun saveLoginBody(loginBody: LoginBody){
        sharedPreferences.edit()
            .putString("email", loginBody.email)
            .putString("password", loginBody.password)
            .apply()
    }

    fun loadLoginBody(): LoginBody?{
        if (!isExistLoginBody())
            return null
        return LoginBody(
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
}