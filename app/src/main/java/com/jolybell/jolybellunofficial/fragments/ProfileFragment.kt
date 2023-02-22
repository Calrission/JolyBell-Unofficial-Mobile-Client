package com.jolybell.jolybellunofficial.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import com.jolybell.jolybellunofficial.databinding.LayoutProfileFragmentBinding
import com.jolybell.jolybellunofficial.models.response.ResponseUser
import com.jolybell.jolybellunofficial.сommon.network.Connection
import com.jolybell.jolybellunofficial.сommon.network.ConnectionController
import com.jolybell.jolybellunofficial.сommon.network.ConnectionController.Companion.push
import com.jolybell.jolybellunofficial.сommon.userdata.Identity

class ProfileFragment(
    fragmentControl: ReplacementFragment.FragmentControl,
    private val onExitCallback: OnExitCallback
) : ReplacementFragmentItem(fragmentControl), OnClickListener {

    lateinit var binding: LayoutProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutProfileFragmentBinding.inflate(inflater, container, false)
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

        binding.exit.setOnClickListener {
            onExitCallback.onExit()
        }

        binding.changePassword.setOnClickListener {

        }
    }

    override fun onClick(v: View?) {

    }
}