package com.jolybell.jolybellunofficial.сommon.network

import com.jolybell.jolybellunofficial.models.*
import com.jolybell.jolybellunofficial.models.body.LoginBody
import com.jolybell.jolybellunofficial.models.body.RegisterBody
import com.jolybell.jolybellunofficial.models.request.QuerySort.Companion.createQuerySortsGetProductsCategory
import com.jolybell.jolybellunofficial.models.response.*
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

    @GET("products")
    fun getProducts(
        @Query("page") page: Int = 1, @Query("per_page") per_page: Int = 100,
        @Query("filters") filters: String,
        @Query("sorts") sorts: String = createQuerySortsGetProductsCategory()
    ): Call<ResponseProducts>

    @GET("products/{productId}")
    fun getProduct(@Path("productId") productId: String): Call<ResponseProduct>

    @GET("products/recommendations/{productId}")
    fun getRecommendation(@Path("productId") productId: String): Call<ResponseRecommendationProducts>
}