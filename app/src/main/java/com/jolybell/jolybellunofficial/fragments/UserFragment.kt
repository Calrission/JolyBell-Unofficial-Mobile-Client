package com.jolybell.jolybellunofficial.fragments

import android.os.Bundle
import android.view.View
import com.jolybell.jolybellunofficial.models.ModelToken
import com.jolybell.jolybellunofficial.models.body.IdentityBody
import com.jolybell.jolybellunofficial.сommon.userdata.Cryptography
import com.jolybell.jolybellunofficial.сommon.userdata.Identity
import com.jolybell.jolybellunofficial.сommon.userdata.SaverUserData

class UserFragment: ReplacementFragment(), OnIdentityCallback, OnExitCallback {

    private val saver by lazy {  SaverUserData(requireContext()) }
    private val cryptography by lazy { Cryptography(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        addFragment(createSubFragment())
    }

    private fun createSubFragment(): ReplacementFragmentItem{
        return if (Identity.isIdentity)
            createProfile()
        else
            createIdentity()
    }

    private fun createProfile(): ProfileFragment = ProfileFragment(fragmentControl, this)
    private fun createIdentity(): IdentityFragment = IdentityFragment(fragmentControl, this)

    override fun onAuth(identityBody: IdentityBody, token: ModelToken, isSaveData: Boolean) {
        Identity.token = token
        if (isSaveData){
//            val encodeLoginBody = identityBody.encode(cryptography)
            saver.saveModelToken(token)
            saver.saveIdentityBody(identityBody)
        }
        replaceFragment(createProfile())
    }

    override fun onExit() {
        Identity.isIdentity = false
        Identity.token = null
        replaceFragment(createIdentity())
    }
}

interface OnIdentityCallback{
    fun onAuth(identityBody: IdentityBody, token: ModelToken, isSaveData: Boolean)
}

interface OnExitCallback{
    fun onExit()
}