package com.jolybell.jolybellunofficial.сommon

import java.text.DecimalFormat

class DoubleUtils {
    companion object {
        fun Double.toBeautifulString(): String{
            return DecimalFormat("#0.00").format(this)
        }
    }
}