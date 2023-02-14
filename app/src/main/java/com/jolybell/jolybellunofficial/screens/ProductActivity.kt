package com.jolybell.jolybellunofficial.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jolybell.jolybellunofficial.databinding.ActivityProductBinding
import com.jolybell.jolybellunofficial.models.ModelProduct
import com.jolybell.jolybellunofficial.сommon.VersionHelper.Companion.getSerializableVersion

class ProductActivity : AppCompatActivity() {

    private lateinit var model: ModelProduct
    private lateinit var binding: ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = intent.extras!!.getSerializableVersion("model", ModelProduct::class.java)!!

    }
}