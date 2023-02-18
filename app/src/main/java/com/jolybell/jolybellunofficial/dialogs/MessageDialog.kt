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
    private val dismiss: Boolean = true
): Dialog(context, themeRes) {

    private lateinit var binding: DialogMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCancelable(dismiss)

        window!!.decorView.setBackgroundResource(android.R.color.transparent)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

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