package com.jolybell.jolybellunofficial.сommon.network

import com.jolybell.jolybellunofficial.models.*
import com.jolybell.jolybellunofficial.models.body.LoginBody
import com.jolybell.jolybellunofficial.models.body.RegisterBody
import com.jolybell.jolybellunofficial.models.request.QueryFilter.Companion.createQueryFiltersGetProductsCategory
import com.jolybell.jolybellunofficial.models.request.QuerySort.Companion.createQuerySortsGetProductsCategory
import com.jolybell.jolybellunofficial.models.response.ResponseAuth
import com.jolybell.jolybellunofficial.models.response.ResponseCategories
import com.jolybell.jolybellunofficial.models.response.ResponseProducts
import com.jolybell.jolybellunofficial.models.response.ResponseUser
import retrofit2.Call
import retrofit2.http.*

interface API {
    @POST("login")
    fun login(@Body loginBody: LoginBody): Call<ResponseAuth>

    @POST("register")
    fun register(@Body registerBody: RegisterBody): Call<ResponseAuth>

    @GET("users/load")
    @Headers("Authorization: ")
    fun getUser(): Call<ResponseUser>

    @GET("categories/load")
    @Headers("Accept-Language: ")
    fun getCategories(): Call<ResponseCategories>

    @GET("products")
    @Headers("x-accept-currency: ")
    fun getProducts(
        category: String,
        @Query("page") page: Int = 1, @Query("per_page") per_page: Int = 100,
        @Query("filters") filters: String = createQueryFiltersGetProductsCategory(category),
        @Query("sorts") sorts: String = createQuerySortsGetProductsCategory()
    ): Call<ResponseProducts>
}