package com.jolybell.jolybellunofficial.сommon.network

import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Connection {

    enum class URLS(val url: String){
        API("https://api.jolybell.com/"),
        IMAGES("https://cdn.jolybell.com/images/")
    }

    val connectionController = ConnectionController()

    val api = connectionController.createApi()
}

class ConnectionController{
    private lateinit var client: OkHttpClient
    private fun getClient(): OkHttpClient{
        if (!::client.isInitialized){
            client = OkHttpClient.Builder()
                .addInterceptor(createInterceptor())
                .build()
        }
        return client
    }

    fun createApi(): API{
        return retrofit.create(API::class.java)
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(Connection.URLS.API.url)
        .client(getClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getPreviewUrlCategory(aliasCategory: String, callback: Callback){
        val request: Request = Request.Builder()
            .url(Connection.URLS.API.url + """products?page=1&per_page=1&filters=[{"type":"and_reletation_field_value_strict","reletation":"category","field":"categories.alias","value":"$aliasCategory"}]&sorts=[{"field":"index","asc":false},{"field":"indexed_at","asc":false}]""")
            .build()

        client.newCall(request).enqueue(callback)
    }

    private fun createInterceptor(): Interceptor{
        return Interceptor {
            val oldRequest = it.request()
            val newRequest: Request
            oldRequest.apply {
                val builder = newBuilder()
                    .header("Authorization", HeadersData.token.toString())
                    .header("Accept-Language", HeadersData.lang)
                    .method(method(), body())
                headers().names().forEach {header ->
                    if (header !in builder.build().headers().names()) {
                        val value = headers().get(header) ?: ""
                        builder.addHeader(header, value)
                    }
                }
                newRequest = builder.build()
            }
            it.proceed(newRequest)
        }
    }
}