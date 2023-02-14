package com.jolybell.jolybellunofficial.сommon.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.jolybell.jolybellunofficial.сommon.network.Connection
import com.jolybell.jolybellunofficial.сommon.network.ConnectionController
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

        fun ImageView.setAliasImage(alias: String){
            setUrlImage(Connection.URLS.getUrlImage(alias))
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
                                setAliasImage(aliasPreview)
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

        fun bitmapFromUrl(context: Context, url: String, onGetData: ConnectionController.OnGetData<Bitmap>){
            Glide.with(context)
                .asBitmap()
                .load(url)
                .into(object: CustomTarget<Bitmap>(){
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?,
                    ) {
                        onGetData.onGetData(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }
                })
        }
    }
}