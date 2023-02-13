package com.jolybell.jolybellunofficial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jolybell.jolybellunofficial.databinding.LayoutCategoryBinding
import com.jolybell.jolybellunofficial.models.ModelCategory
import com.jolybell.jolybellunofficial.сommon.VersionHelper.Companion.getSerializableVersion

class CategoryFragment(
    fragmentControl: ReplacementFragment.FragmentControl
) : ReplacementFragmentItem(fragmentControl) {

    private lateinit var binding: LayoutCategoryBinding

    private lateinit var model: ModelCategory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutCategoryBinding.inflate(inflater, container, false)
        model = requireArguments().getSerializableVersion("model", ModelCategory::class.java)!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.back.setOnClickListener {
            fragmentControl.finishCurrent()
        }
        binding.titleCategory.text = model.name
    }
}