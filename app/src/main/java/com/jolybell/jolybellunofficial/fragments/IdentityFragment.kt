package com.jolybell.jolybellunofficial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import com.jolybell.jolybellunofficial.R
import com.jolybell.jolybellunofficial.databinding.LayoutIdentityFragmentBinding
import com.jolybell.jolybellunofficial.dialogs.AlertMessageDialog.Companion.getAlertMessageDialogForError
import com.jolybell.jolybellunofficial.models.body.IdentityBody
import com.jolybell.jolybellunofficial.models.body.LoginBody
import com.jolybell.jolybellunofficial.models.body.LoginBody.Companion.toLoginBody
import com.jolybell.jolybellunofficial.models.body.RegisterBody
import com.jolybell.jolybellunofficial.models.body.RegisterBody.Companion.toRegisterBody
import com.jolybell.jolybellunofficial.models.response.ResponseIdentity
import com.jolybell.jolybellunofficial.сommon.network.Connection
import com.jolybell.jolybellunofficial.сommon.network.ConnectionController
import com.jolybell.jolybellunofficial.сommon.network.ConnectionController.Companion.push

class IdentityFragment(
    fragmentControl: ReplacementFragment.FragmentControl,
    private val identityCallback: OnIdentityCallback
) : ReplacementFragmentItem(fragmentControl), OnClickListener {

    private lateinit var binding: LayoutIdentityFragmentBinding
    private var mode: Mode = Mode.None
    set(value) {
        field = value
        value.instance.doWork(binding.motion)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = LayoutIdentityFragmentBinding.inflate(layoutInflater, container, false)
        mode = Mode.SignIn
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.signIn.setOnClickListener(this)
        binding.signUp.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if ((v == binding.signUp && mode == Mode.SignUp) || (v == binding.signIn && mode == Mode.SignIn))
            doIdentity()
        else
            changeMode()
    }

    private fun changeMode(){
        mode = when (mode){
            Mode.SignIn -> Mode.SignUp
            Mode.SignUp -> Mode.SignIn
            else -> Mode.None
        }
    }

    private fun doIdentity(){
        val email = binding.email.text
        val password = binding.password.text
        val body = IdentityBody(email, password)
        mode.instance.doIdentity(body, object: ConnectionController.OnGetData<ResponseIdentity>{
            override fun onGetData(model: ResponseIdentity) {
                val token = model.data
                if (token != null)
                    identityCallback.onAuth(body, token, !binding.dontSave.isChecked)
                else
                    requireContext().getAlertMessageDialogForError("token null").show()
            }

            override fun onError(error: String) {
                requireContext().getAlertMessageDialogForError(error).show()
            }
        })
    }

    enum class Mode(val instance: IdentityMode<IdentityBody>) {
        SignIn(SignIn()),
        SignUp(SignUp()),
        None(None())
    }

    companion object{
        abstract class IdentityMode <out T: IdentityBody> {
            abstract fun doWork(motion: MotionLayout)

            abstract fun doIdentity(identityBody: IdentityBody, onGetData: ConnectionController.OnGetData<ResponseIdentity>)

            abstract fun castIdentityBody(identityBody: IdentityBody): T
        }
        class SignIn: IdentityMode<LoginBody>() {
            override fun doWork(motion: MotionLayout) {
                motion.setTransition(R.id.tra_identity)
                motion.transitionToStart()
            }

            override fun doIdentity(identityBody: IdentityBody, onGetData: ConnectionController.OnGetData<ResponseIdentity>) {
                Connection.api.login(castIdentityBody(identityBody)).push(onGetData)
            }

            override fun castIdentityBody(identityBody: IdentityBody): LoginBody {
                return identityBody.toLoginBody()
            }
        }
        class SignUp: IdentityMode<RegisterBody>() {
            override fun doWork(motion: MotionLayout) {
                motion.setTransition(R.id.tra_identity)
                motion.transitionToEnd()
            }

            override fun doIdentity(
                identityBody: IdentityBody,
                onGetData: ConnectionController.OnGetData<ResponseIdentity>,
            ) {
                Connection.api.register(castIdentityBody(identityBody)).push(onGetData)
            }

            override fun castIdentityBody(identityBody: IdentityBody): RegisterBody {
                return identityBody.toRegisterBody()
            }
        }
        class None: IdentityMode <IdentityBody>(){
            override fun doWork(motion: MotionLayout) {

            }

            override fun castIdentityBody(identityBody: IdentityBody): IdentityBody = identityBody

            override fun doIdentity(
                identityBody: IdentityBody,
                onGetData: ConnectionController.OnGetData<ResponseIdentity>,
            ) {

            }

        }
    }


}
