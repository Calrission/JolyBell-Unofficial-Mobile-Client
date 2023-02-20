package com.jolybell.jolybellunofficial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jolybell.jolybellunofficial.databinding.LayoutReplacementFragmentBinding
import com.jolybell.jolybellunofficial.models.ModelToken
import com.jolybell.jolybellunofficial.models.body.LoginBody
import com.jolybell.jolybellunofficial.сommon.userdata.Cryptography
import com.jolybell.jolybellunofficial.сommon.userdata.Identity
import com.jolybell.jolybellunofficial.сommon.userdata.SaverUserData

class UserFragment: ReplacementFragment(), OnAuthCallback, OnExitCallback {

    private val saver by lazy {  SaverUserData(requireContext()) }
    private val cryptography by lazy { Cryptography(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        replaceFragment(createSubFragment())
    }

    private fun createSubFragment(): ReplacementFragmentItem{
        return if (Identity.isIdentity)
            createProfile()
        else
            createIdentity()
    }

    private fun createProfile(): ProfileFragment = ProfileFragment(fragmentControl, this)
    private fun createIdentity(): IdentityFragment = IdentityFragment(fragmentControl, this)

    override fun onAuth(loginBody: LoginBody, token: ModelToken, isSaveData: Boolean) {
        Identity.token = token
        if (isSaveData){
            val encodeLoginBody = loginBody.encode(cryptography)
            saver.saveLoginBody(encodeLoginBody)
        }
        replaceFragment(createProfile())
    }

    override fun onExit() {
        Identity.isIdentity = false
        Identity.token = null
        replaceFragment(createIdentity())
    }
}

interface OnAuthCallback{
    fun onAuth(loginBody: LoginBody, token: ModelToken, isSaveData: Boolean)
}

interface OnExitCallback{
    fun onExit()
}