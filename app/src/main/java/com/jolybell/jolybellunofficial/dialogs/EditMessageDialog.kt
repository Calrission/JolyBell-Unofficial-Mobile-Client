package com.jolybell.jolybellunofficial.dialogs

import android.content.Context
import android.os.Bundle
import com.jolybell.jolybellunofficial.databinding.DialogEditMessageBinding

class EditMessageDialog(
    context: Context,
    private val title: String,
    private val hint: String,
    private val text: String = "",
    private val typeEditText: Int,
    var onCallback: OnCallback? = null
) : BaseDialog(context) {

    private lateinit var binding: DialogEditMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DialogEditMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editText.inputType = typeEditText
        binding.editText.text = text
        binding.editText.hint = hint

        binding.titleEditDialog.text = title

        binding.btnOK.setOnClickListener {
            val isDismiss = onCallback?.onApply(binding.editText.text) ?: true
            if (isDismiss)
                dismiss()
        }

        binding.btnCancel.setOnClickListener {
            onCallback?.onCancel()
            dismiss()
        }
    }

    interface OnCallback{
        fun onApply(text: String): Boolean
        fun onCancel()
    }
}