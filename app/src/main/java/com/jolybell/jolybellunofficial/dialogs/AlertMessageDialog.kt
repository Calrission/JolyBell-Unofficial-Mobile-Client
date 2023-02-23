package com.jolybell.jolybellunofficial.dialogs

import android.content.Context
import android.os.Bundle
import com.jolybell.jolybellunofficial.R

class AlertMessageDialog(
    context: Context,
    themeRes: Int = R.style.CustomDialogTheme,
    dismiss: Boolean = true
): BaseDialog(context, themeRes, dismiss) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}