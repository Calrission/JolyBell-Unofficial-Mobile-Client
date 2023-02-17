package com.jolybell.jolybellunofficial.сommon.utils

import com.jolybell.jolybellunofficial.сommon.network.HeadersData
import java.text.DecimalFormat

class DoubleUtils {
    companion object {
        fun Double.toBeautifulString(): String{
            return DecimalFormat("#0.00").format(this)
        }

        fun Double.withCurrency(): String{
            return "${this.toBeautifulString()} ${HeadersData.currency}"
        }
    }
}