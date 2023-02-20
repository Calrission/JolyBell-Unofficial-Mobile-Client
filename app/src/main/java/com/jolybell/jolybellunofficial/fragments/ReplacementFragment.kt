package com.jolybell.jolybellunofficial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.jolybell.jolybellunofficial.R
import com.jolybell.jolybellunofficial.databinding.LayoutReplacementFragmentBinding
import java.io.Serializable

open class ReplacementFragment(
    private val callback: Callback? = null
): Fragment() {

    protected lateinit var binding: LayoutReplacementFragmentBinding

     val fragmentControl = object: FragmentControl{
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
        binding.frame.id = ViewCompat.generateViewId()
        callback?.onPrepared(this)
        return binding.root
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
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .replace(binding.frame.id, fragment)
            .commit()
    }

    fun finish(){
        val last = parentFragmentManager.fragments.last { it.isVisible }
        parentFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
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