package com.jolybell.jolybellunofficial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jolybell.jolybellunofficial.databinding.LayoutIdentityFragmentBinding

class IdentityFragment(
    fragmentControl: ReplacementFragment.FragmentControl,
    private val authCallback: OnAuthCallback
) : ReplacementFragmentItem(fragmentControl) {

    private lateinit var binding: LayoutIdentityFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = LayoutIdentityFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding
    }
}