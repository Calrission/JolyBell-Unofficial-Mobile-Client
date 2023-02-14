package com.jolybell.jolybellunofficial.сommon.utils

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.core.graphics.get
import androidx.core.graphics.toColor
import java.io.Serializable

class VersionHelper {
    companion object {
        fun <T: Serializable> Bundle.getSerializableVersion(key: String, clas: Class<T>): T?{
            return if (android.os.Build.VERSION.SDK_INT >= 33){
                getSerializable(key, clas)
            }else{
                clas.cast(getSerializable(key))
            }
        }

        fun Bitmap.getColorPx(x: Int, y: Int): Color {
            return if (android.os.Build.VERSION_CODES.Q <= android.os.Build.VERSION.SDK_INT){
                getColorPx(x, y)
            }else{
                val colorInt = get(x, y)
                Color.rgb(Color.red(colorInt), Color.green(colorInt), Color.blue(colorInt)).toColor()
            }
        }
    }
}