package com.jolybell.jolybellunofficial.сommon.network

import com.jolybell.jolybellunofficial.models.*
import com.jolybell.jolybellunofficial.models.body.LoginBody
import com.jolybell.jolybellunofficial.models.body.RegisterBody
import com.jolybell.jolybellunofficial.models.request.QuerySort.Companion.createQuerySortsGetProductsCategory
import com.jolybell.jolybellunofficial.models.response.*
import com.jolybell.jolybellunofficial.сommon.userdata.Identity
import retrofit2.Call
import retrofit2.http.*

interface API {
    @POST("auth/login")
    fun login(@Body loginBody: LoginBody): Call<ResponseIdentity>

    @POST("auth/register")
    fun register(@Body registerBody: RegisterBody): Call<ResponseIdentity>

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

    @PUT("users/{userId}")
    fun updateUser(
        @Path("userId") userId: String = Identity.user!!.id,
        @Body body: UpdateModelUser = Identity.user!!.toUpdateModelUser()
    ): Call<ResponseNotification>
}