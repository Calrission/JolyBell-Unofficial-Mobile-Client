package com.jolybell.jolybellunofficial.сommon.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.graphics.*
import androidx.core.graphics.drawable.toDrawable
import com.jolybell.jolybellunofficial.сommon.utils.VersionHelper.Companion.getColorPx

class ColorUtils {
    companion object {
        fun Context.getColor(@ColorRes color: Int): Int {
            return ContextCompat.getColor(this, color)
        }

        fun Bitmap.getAverageColor(): Color{
            var sumR = 0
            var sumG = 0
            var sumB = 0

            (0 until width).forEach { x ->
                (0 until height).forEach { y ->
                    val colorInt = get(x, y)
                    sumR += Color.red(colorInt)
                    sumG += Color.green(colorInt)
                    sumB += Color.blue(colorInt)
                }
            }

            val count = width * height
            sumR /= count
            sumG /= count
            sumB /= count

            return Color.rgb(sumR, sumG, sumB).toColor()
        }

        fun Color.isLight(): Boolean{
            val red = this.component1() * 255
            val green = this.component2() * 255
            val blue = this.component3() * 255
            return (1 - (0.299 * red + 0.587 * green + 0.114 * blue) / 255 < 0.5)
        }
    }
}