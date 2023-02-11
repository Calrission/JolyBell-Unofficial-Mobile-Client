package com.jolybell.jolybellunofficial.сommon.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Connection {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.jolybell.com/")
        .client(createInterceptor())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(API::class.java)

    private fun createInterceptor(): OkHttpClient{
        val interceptor = OkHttpClient()
        interceptor.interceptors().add(Interceptor {
            val oldRequest = it.request()
            val newRequest: Request = oldRequest.apply {
                val builder = newBuilder()
                    .header("Authorization", Info.token.toString())
                    .header("Accept-Language", Info.lang)
                    .method(method(), body())
                headers().names().forEach {header ->
                    if (header !in newBuilder().build().headers().names()) {
                        val value = headers().get(header) ?: ""
                        builder.addHeader(header, value)
                    }
                }
                builder.build()
            }
            it.proceed(newRequest)
        })
        return interceptor
    }
}