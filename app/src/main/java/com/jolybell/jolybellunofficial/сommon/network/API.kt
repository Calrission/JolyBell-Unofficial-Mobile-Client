package com.jolybell.jolybellunofficial.сommon.network

import com.jolybell.jolybellunofficial.models.*
import com.jolybell.jolybellunofficial.models.body.LoginBody
import com.jolybell.jolybellunofficial.models.body.RegisterBody
import com.jolybell.jolybellunofficial.models.response.ResponseAuth
import com.jolybell.jolybellunofficial.models.response.ResponseCategories
import com.jolybell.jolybellunofficial.models.response.ResponseUser
import retrofit2.Call
import retrofit2.http.*

interface API {
    @POST("login")
    fun login(@Body loginBody: LoginBody): Call<ResponseAuth>

    @POST("register")
    fun register(@Body registerBody: RegisterBody): Call<ResponseAuth>

    @GET("users/load")
    fun getUser(): Call<ResponseUser>

    @GET("categories/load")
    fun getCategories(): Call<ResponseCategories>
}