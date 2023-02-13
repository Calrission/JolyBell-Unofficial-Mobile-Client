package com.jolybell.jolybellunofficial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jolybell.jolybellunofficial.R
import com.jolybell.jolybellunofficial.databinding.LayoutReplacementFragmentBinding

class ReplacementFragment(
    private val callback: Callback
): Fragment() {

    private lateinit var binding: LayoutReplacementFragmentBinding
    private val fragmentControl = object: FragmentControl{
        override fun <T: ReplacementFragmentItem> changeFragment(fragment: Class<T>) {
            replaceFragment(fragment)
        }

        override fun removeFragment(fragment: Fragment) {

        }

        override fun popBackStack() {
            popBackStack()
        }
    }

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

    fun <T: ReplacementFragmentItem> replaceFragment(fragment: Class<T>){
        replaceFragment(fragment.constructors[0].newInstance(fragmentControl) as ReplacementFragmentItem)
    }

    fun replaceFragment(fragment: ReplacementFragmentItem){
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

    interface FragmentControl{
        fun <T: ReplacementFragmentItem> changeFragment(fragment: Class<T>)
        fun removeFragment(fragment: Fragment)

        fun popBackStack()
    }
}