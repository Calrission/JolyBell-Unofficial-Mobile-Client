package com.jolybell.jolybellunofficial.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.appbar.AppBarLayout.LayoutParams
import com.jolybell.jolybellunofficial.R
import com.jolybell.jolybellunofficial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val params = binding.logoTop.motion.layoutParams
        params.height = LayoutParams.WRAP_CONTENT
        binding.logoTop.motion.layoutParams = params
    }
}