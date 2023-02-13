package com.jolybell.jolybellunofficial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jolybell.jolybellunofficial.databinding.LayoutCategoryBinding

class CategoryFragment(
    fragmentControl: ReplacementFragment.FragmentControl
) : ReplacementFragmentItem(fragmentControl) {

    private lateinit var binding: LayoutCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.test.setOnClickListener {
            fragmentControl.finishCurrent()
        }
    }
}