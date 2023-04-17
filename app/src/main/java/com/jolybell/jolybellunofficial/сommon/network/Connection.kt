package com.jolybell.jolybellunofficial.сommon.network

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jolybell.jolybellunofficial.models.ModelNotification
import com.jolybell.jolybellunofficial.models.response.ModelResponse
import com.jolybell.jolybellunofficial.models.response.ModelResponseData
import com.jolybell.jolybellunofficial.models.response.ResponseIdentity
import com.jolybell.jolybellunofficial.models.response.ResponseNotification
import com.jolybell.jolybellunofficial.сommon.userdata.Identity
import com.jolybell.jolybellunofficial.сommon.utils.StringUtils.Companion.unicodeToUtf8
import okhttp3.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object Connection {

    enum class URLS(val url: String){
        API("https://api.jolybell.com/"),
        IMAGES("https://cdn.jolybell.com/images/");
        companion object {
            fun getUrlImage(alias: String): String{
                return Connection.URLS.IMAGES.url + alias + ".webp"
            }
        }
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
            val newRequest = it.request().fillHeaders()
            it.proceed(newRequest)
        }
    }

    private fun Request.fillHeaders(): Request{

        val newHeaders = mutableMapOf<String, String>()
        val oldHeaders = headers()
        oldHeaders.names().forEach {
            newHeaders[it] = oldHeaders[it]!!
        }
        newHeaders["Authorization"] = Identity.token?.getBearer() ?: ""
        newHeaders["Accept-Language"] = HeadersData.lang
        newHeaders["x-accept-currency"] = HeadersData.currency

        return newBuilder()
            .headers(Headers.of(newHeaders))
            .method(method(), body())
            .build()
    }

    companion object {
        fun <T: ModelResponse> Call<T>.push(onGetData: OnGetData<T>){
            enqueue(object: retrofit2.Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null && body.result){
                        onGetData.onGetData(body)
                    }else{
                        if (body is ResponseIdentity)
                            onGetData.onError(body.notification.message)
                        else if (body is ResponseNotification){
                            onGetData.onError(body.notification.message)
                        } else if(response.errorBody() != null){
                            val modelResponse = Gson().fromJson(response.errorBody()!!.string(), ModelResponseData::class.java)
                            onGetData.onError("${response.code()} - ${modelResponse.result}")
                        }else{
                            onGetData.onError(
                                "code=${response.code()} " +
                                        "url=${call.request().url()} " +
                                        "query_params=${call.request().url().queryParameterNames()}"
                            )
                        }

                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    onGetData.onError(t.localizedMessage ?: t.message ?: "unknown error")
                }
            })
        }
    }

    interface OnGetData <T>{
        fun onGetData(model: T)
        fun onError(error: String)
    }
}