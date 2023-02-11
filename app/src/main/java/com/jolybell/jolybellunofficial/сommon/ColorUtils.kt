package com.jolybell.jolybellunofficial.сommon

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

class ColorUtils {
    companion object {
        fun Context.getColor(@ColorRes color: Int): Int {
            return ContextCompat.getColor(this, color)
        }
    }
}