package com.jolybell.jolybellunofficial.сommon.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.*

class ColorUtils {
    companion object {
        fun Context.getColor(@ColorRes color: Int): Int {
            return ContextCompat.getColor(this, color)
        }

        fun Bitmap.getAverageRGB(): Triple<Int, Int, Int>{
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

            return Triple(sumR, sumG, sumB)
        }

        fun Triple<Int, Int, Int>.isLight(): Boolean{
            return (1 - (0.299 * first + 0.587 * second + 0.114 * third) / 255 < 0.5)
        }
    }
}