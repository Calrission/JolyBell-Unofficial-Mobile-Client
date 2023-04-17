package com.jolybell.jolybellunofficial.dialogs

import android.content.Context
import android.os.Bundle
import com.jolybell.jolybellunofficial.databinding.DialogChangePasswordBinding

class ChangePasswordDialog(context: Context, var callback: Callback? = null) : BaseDialog(context) {
    lateinit var binding: DialogChangePasswordBinding

    var oldPassword: String
        get() = binding.oldPassword.text
        set(value) {
            binding.oldPassword.text = value
        }

    var newPassword: String
        get() = binding.newPassword.text
        set(value) {
            binding.newPassword.text = value
        }

    var confirmNewPassword: String
        get() = binding.confirmPassword.text
        set(value) {
            binding.confirmPassword.text = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOK.setOnClickListener {
            callback?.onClickOK(oldPassword, newPassword, confirmNewPassword)
            dismiss()
        }

        binding.btnCancel.setOnClickListener {
            callback?.onClickCancel()
            dismiss()
        }
    }

    interface Callback{
        fun onClickOK(oldPassword: String, newPassword: String, confirmNewPassword: String)
        fun onClickCancel()
    }
}