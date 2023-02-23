package com.jolybell.jolybellunofficial.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.jolybell.jolybellunofficial.R

open class BaseDialog(
    context: Context,
    themeRes: Int = R.style.CustomDialogTheme,
    private val dismiss: Boolean = true
): Dialog(context, themeRes) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCancelable(dismiss)

        window!!.decorView.setBackgroundResource(android.R.color.transparent)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
    }
}