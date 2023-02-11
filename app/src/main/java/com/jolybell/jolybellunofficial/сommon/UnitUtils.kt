package com.jolybell.jolybellunofficial.сommon

import android.content.Context
import android.util.DisplayMetrics


class UnitUtils {
    companion object {
        fun dpToPx(dp: Float, context: Context): Float {
            return dp * (context.resources
                .displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }
        
        fun pxToDp(px: Float, context: Context): Float {
            return px / (context.resources
                .displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }
    }
}