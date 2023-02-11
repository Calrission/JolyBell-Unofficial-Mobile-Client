package com.jolybell.jolybellunofficial.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jolybell.jolybellunofficial.R
import com.jolybell.jolybellunofficial.databinding.ActivityMainBinding
import com.jolybell.jolybellunofficial.views.OnFinishAnimation
import com.jolybell.jolybellunofficial.сommon.UnitUtils.Companion.dpToPx

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}