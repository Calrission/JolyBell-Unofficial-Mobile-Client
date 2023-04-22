package com.jolybell.jolybellunofficial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.children
import com.jolybell.jolybellunofficial.databinding.FragmentLayoutProfileBinding
import com.jolybell.jolybellunofficial.dialogs.AlertMessageDialog
import com.jolybell.jolybellunofficial.dialogs.AlertMessageDialog.Companion.getAlertMessageDialogForError
import com.jolybell.jolybellunofficial.dialogs.ChangePasswordDialog
import com.jolybell.jolybellunofficial.dialogs.EditMessageDialog
import com.jolybell.jolybellunofficial.dialogs.MessageDialog
import com.jolybell.jolybellunofficial.models.response.ModelUser
import com.jolybell.jolybellunofficial.models.response.ResponseNotification
import com.jolybell.jolybellunofficial.models.response.UpdateModelUser
import com.jolybell.jolybellunofficial.views.EditableTextView
import com.jolybell.jolybellunofficial.сommon.network.Connection
import com.jolybell.jolybellunofficial.сommon.network.ConnectionController
import com.jolybell.jolybellunofficial.сommon.network.ConnectionController.Companion.push
import com.jolybell.jolybellunofficial.сommon.userdata.Identity

class ProfileFragment(
    fragmentControl: ReplacementFragment.FragmentControl,
    private val onExitCallback: OnExitCallback
) : ReplacementFragmentItem(fragmentControl), OnClickListener {

    lateinit var binding: FragmentLayoutProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLayoutProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Identity.user?.apply {
            binding.email.text = email
            binding.lastname.text = last_name ?: ""
            binding.firstname.text = first_name ?: ""
            binding.patronymic.text = middle_name ?: ""
            binding.phone.text = phone_number ?: ""
            binding.country.text = country?.name ?: ""
            binding.city.text = city ?: ""
            binding.region.text = region ?: ""
            binding.address.text = address ?: ""
            binding.postIndex.text = zip_code ?: ""
        }

        binding.linContent.children.forEach {
            if (it is EditableTextView && it.id != binding.country.id){
                it.setOnClickListener(this)
            }
        }

        binding.exit.setOnClickListener {
            onExitCallback.onExit()
        }

        binding.changePassword.setOnClickListener {
            val dialog = ChangePasswordDialog(requireContext(), object: ChangePasswordDialog.Callback{
                override fun onClickOK(
                    newPassword: String,
                    confirmNewPassword: String,
                ) {
                    with(requireContext()) {
                        if (newPassword.isEmpty())
                            getAlertMessageDialogForError("Введите новый пароль!")
                        else if (confirmNewPassword.isEmpty())
                            getAlertMessageDialogForError("Введите пароль еще раз!")
                        else if (confirmNewPassword != newPassword)
                            getAlertMessageDialogForError("Пароль не совпадают!")
                        else {
                            Connection.api.updateUser(
                                Identity.user?.id ?: "0",
                                UpdateModelUser(password = newPassword, password_confirmation = newPassword)
                            ).push(object: ConnectionController.OnGetData<ResponseNotification>{
                                override fun onGetData(model: ResponseNotification) {
                                    MessageDialog(requireContext(),model.notification.toModelMessage()).show()
                                }

                                override fun onError(error: String) {
                                    requireContext().getAlertMessageDialogForError(error).show()
                                }
                            })
                        }
                    }
                }

                override fun onClickCancel() {}
            })
            dialog.show()
        }
    }

    private fun createEditMessageDialog(editText: EditableTextView): EditMessageDialog{
        return EditMessageDialog(
            requireContext(), "Изменить", editText.hint, editText.text,
            editText.inputType,
            object: EditMessageDialog.OnCallback{
                override fun onApply(text: String): Boolean {
                    if (text.isEmpty()) {
                        Toast.makeText(requireContext(), "Поле не должно быть пустым", Toast.LENGTH_LONG).show()
                        return false
                    }
                    editText.text = text
                    saveChanged()
                    return true
                }

                override fun onCancel() {}
            }
        )
    }

    private fun saveChanged(){
        val newUser = Identity.user!!.copy()
        fillUser(newUser)
        Connection.api.updateUser(body = newUser.toUpdateModelUser()).push(object: ConnectionController.OnGetData<ResponseNotification>{
            override fun onGetData(model: ResponseNotification) {
                AlertMessageDialog(
                    requireContext(),
                    title = model.notification.type,
                    message = model.notification.message
                ).show()
                Identity.user = newUser
            }

            override fun onError(error: String) {
                requireContext().getAlertMessageDialogForError(error).show()
            }
        })
    }

    private fun fillUser(user: ModelUser){
        with(user){
            first_name = binding.firstname.text
            last_name = binding.lastname.text
            middle_name = binding.patronymic.text
            email = binding.email.text
            phone_number = binding.phone.text
            city = binding.city.text
            region = binding.region.text
            address = binding.address.text
            zip_code = binding.postIndex.text
        }
    }

    override fun onClick(v: View?) {
        createEditMessageDialog(v as EditableTextView).show()
    }
}