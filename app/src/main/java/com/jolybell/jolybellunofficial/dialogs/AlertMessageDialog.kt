package com.jolybell.jolybellunofficial.dialogs

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.jolybell.jolybellunofficial.R
import com.jolybell.jolybellunofficial.databinding.DialogAlertMessageBinding

class AlertMessageDialog(
    context: Context,
    themeRes: Int = R.style.CustomDialogTheme,
    dismiss: Boolean = true,
    val title: String,
    val message: String,
    val showCancel: Boolean,
    val onClickOk: OnClickListener?,
    val onClickCancel: OnClickListener?
): BaseDialog(context, themeRes, dismiss) {

    private lateinit var binding: DialogAlertMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DialogAlertMessageBinding.inflate(layoutInflater)

        binding.titleMessageDialog.text = title
        binding.messageDialog.text = message

        binding.btnOK.setOnClickListener(onClickOk)
        binding.btnCancel.setOnClickListener(onClickCancel)

        binding.btnCancel.visibility = if (showCancel) View.GONE else View.VISIBLE
    }
}