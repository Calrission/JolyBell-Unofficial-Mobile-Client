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
    val showCancel: Boolean = true,
    val dismissWhenClickable: Boolean = true,
    val onClickOk: OnClickListener? = null,
    val onClickCancel: OnClickListener? = null
): BaseDialog(context, themeRes, dismiss) {

    companion object {
        fun Context.getInstanceForError(error: String): AlertMessageDialog = AlertMessageDialog(
            this,
            title = "Ошибка",
            message = error,
            showCancel = false
        )
    }

    private lateinit var binding: DialogAlertMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DialogAlertMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.titleMessageDialog.text = title
        binding.messageDialog.text = message

        binding.btnOK.setOnClickListener {
            onClickOk?.onClick(it)
            if (dismissWhenClickable)
                dismiss()
        }

        binding.btnCancel.setOnClickListener{
            onClickCancel?.onClick(it)
            if (dismissWhenClickable)
                dismiss()
        }

        binding.btnCancel.visibility = if (showCancel) View.GONE else View.VISIBLE
    }
}