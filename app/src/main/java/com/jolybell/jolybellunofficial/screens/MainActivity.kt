package com.jolybell.jolybellunofficial.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.children
import com.jolybell.jolybellunofficial.adapters.FragmentAdapter
import com.jolybell.jolybellunofficial.databinding.ActivityMainBinding
import com.jolybell.jolybellunofficial.fragments.DeliveryFragment
import com.jolybell.jolybellunofficial.fragments.FragmentListCategories
import com.jolybell.jolybellunofficial.fragments.UserFragment
import com.jolybell.jolybellunofficial.fragments.ReplacementFragment
import com.jolybell.jolybellunofficial.сommon.userdata.Identity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Identity.load(this)

        binding.mainPager.isUserInputEnabled = false

        binding.bottomNavigationView.setOnItemSelectedListener {
            binding.mainPager.setCurrentItem(binding.bottomNavigationView.menu.children.toList().indexOf(it), true)
            return@setOnItemSelectedListener true
        }

        binding.mainPager.adapter = FragmentAdapter(this,
            listOf(
                ReplacementFragment(object: ReplacementFragment.Callback{
                    override fun onPrepared(fragment: ReplacementFragment) {
                        fragment.replaceFragment(FragmentListCategories::class.java)
                    }
                }),
                DeliveryFragment(),
                UserFragment()
            )
        )
    }
}