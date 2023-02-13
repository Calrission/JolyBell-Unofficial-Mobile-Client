package com.jolybell.jolybellunofficial.сommon

import android.app.Activity
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.jolybell.jolybellunofficial.сommon.network.Connection
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException


class ImageUtils {
    companion object {
        const val TAG_PREVIEW_CATEGORY = "image-preview-category"

        fun ImageView.setUrlImage(url: String){

            Glide.with(this)
                .load(url)
                .override(com.bumptech.glide.request.target.Target.SIZE_ORIGINAL)
                .into(this)
        }

        fun ImageView.setAsyncPreviewCategory(activity: Activity, categoryAlias: String, clearBefore: Boolean = true){
            if (clearBefore)
                setImageDrawable(null)

            Connection.connectionController.getPreviewUrlCategory(categoryAlias, object: Callback{
                override fun onFailure(call: Call, e: IOException) {
                    Log.e(TAG_PREVIEW_CATEGORY, e.message.toString())
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body()
                    if (body != null){
                        val json = JSONObject(body.string())
                        if (json.getBoolean("result")){
                            val aliasPreview = json
                                .getJSONArray("data")
                                .getJSONObject(0)
                                .getJSONArray("images")
                                .getJSONObject(0)
                                .getString("alias")
                            activity.runOnUiThread {
                                setUrlImage("${Connection.URLS.IMAGES.url}${aliasPreview}.webp?")
                            }
                        }else{
                            Log.e(TAG_PREVIEW_CATEGORY, "Response response false")
                        }
                    }else{
                        Log.e(TAG_PREVIEW_CATEGORY, "Response body null")
                    }
                }
            })
        }
    }
}