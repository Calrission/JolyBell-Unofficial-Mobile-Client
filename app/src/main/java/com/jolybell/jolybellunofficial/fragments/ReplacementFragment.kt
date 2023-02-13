package com.jolybell.jolybellunofficial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jolybell.jolybellunofficial.R
import com.jolybell.jolybellunofficial.databinding.LayoutReplacementFragmentBinding
import java.io.Serializable

class ReplacementFragment(
    private val callback: Callback
): Fragment() {

    private lateinit var binding: LayoutReplacementFragmentBinding
    private val fragmentControl = object: FragmentControl{
        override fun <T: ReplacementFragmentItem> changeFragment(fragment: Class<T>, args: Map<String, Any>) {
            replaceFragment(fragment, args)
        }

        override fun removeFragment(fragment: Fragment) {

        }

        override fun finishCurrent() {
            finish()
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

    fun <T: ReplacementFragmentItem> replaceFragment(fragment: Class<T>, args: Map<String, Any> = mapOf()){
        val bundle = Bundle().fillArgs(args)
        val instance = fragment.constructors[0].newInstance(fragmentControl) as ReplacementFragmentItem
        instance.arguments = bundle
        replaceFragment(instance)
    }

    private fun Bundle.fillArgs(args: Map<String, Any>): Bundle{
        args.keys.forEach {
        when (val value = args[it]) {
                is Int -> putInt(it, value)
                is String -> putString(it, value)
                is Boolean -> putBoolean(it, value)
                is Serializable -> putSerializable(it, value)
            }
        }
        return this
    }

    fun replaceFragment(fragment: ReplacementFragmentItem){
        parentFragmentManager.beginTransaction()
            .add(R.id.frame, fragment)
            .commit()
    }

    fun finish(){
        val last = parentFragmentManager.fragments.last { it.isVisible }
        parentFragmentManager.beginTransaction()
            .remove(last)
            .commit()
    }

    interface Callback{
        fun onPrepared(fragment: ReplacementFragment)
    }

    interface FragmentControl{
        fun <T: ReplacementFragmentItem> changeFragment(fragment: Class<T>, args: Map<String, Any> = mapOf())
        fun removeFragment(fragment: Fragment)

        fun finishCurrent()
    }
}