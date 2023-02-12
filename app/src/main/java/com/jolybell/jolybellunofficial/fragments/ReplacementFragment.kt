package com.jolybell.jolybellunofficial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jolybell.jolybellunofficial.R
import com.jolybell.jolybellunofficial.databinding.LayoutReplacementFragmentBinding

class ReplacementFragment(private val callback: Callback): Fragment() {

    private lateinit var binding: LayoutReplacementFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutReplacementFragmentBinding.inflate(inflater, container, false)
        callback.onPrepared(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    fun replaceFragment(fragment: Fragment){
        parentFragmentManager.beginTransaction()
            .add(R.id.frame, fragment)
            .commit()
    }

    fun popBackStack(){
        parentFragmentManager.popBackStack()
    }

    interface Callback{
        fun onPrepared(fragment: ReplacementFragment)
    }
}