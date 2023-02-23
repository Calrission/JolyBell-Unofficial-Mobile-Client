package com.jolybell.jolybellunofficial.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.jolybell.jolybellunofficial.R
import com.jolybell.jolybellunofficial.databinding.DialogMessageBinding

class MessageDialog(
    context: Context,
    private val model: ModelMessage,
    themeRes: Int = R.style.CustomDialogTheme,
    dismiss: Boolean = true
): BaseDialog(context, themeRes, dismiss) {

    private lateinit var binding: DialogMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DialogMessageBinding.inflate(layoutInflater)

        setContentView(binding.root)

        model.apply {
            binding.titleMessageDialog.text = title
            binding.messageDialog.text = message
        }
    }

    data class ModelMessage(
        val title: String,
        val message: String
    )
}