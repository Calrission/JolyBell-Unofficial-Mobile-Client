package com.jolybell.jolybellunofficial.fragments

import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.jolybell.jolybellunofficial.adapters.ImagesAdapter
import com.jolybell.jolybellunofficial.adapters.MutableAdapter
import com.jolybell.jolybellunofficial.adapters.RecommendationsAdapter
import com.jolybell.jolybellunofficial.databinding.LayoutProductBinding
import com.jolybell.jolybellunofficial.models.ModelProduct
import com.jolybell.jolybellunofficial.models.ModelProductShort
import com.jolybell.jolybellunofficial.models.response.ResponseRecommendationProducts
import com.jolybell.jolybellunofficial.screens.ProductActivity.Companion.TAG
import com.jolybell.jolybellunofficial.сommon.network.Connection
import com.jolybell.jolybellunofficial.сommon.network.ConnectionController
import com.jolybell.jolybellunofficial.сommon.network.ConnectionController.Companion.push

class ProductFragment(private val model: ModelProduct, private val theme: Int): Fragment() {

    lateinit var binding: LayoutProductBinding
    private lateinit var imagesAdapter: ImagesAdapter
    private lateinit var recommendationAdapter: RecommendationsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imagesAdapter = ImagesAdapter()
        recommendationAdapter = RecommendationsAdapter(theme, object: MutableAdapter.OnClick<ModelProductShort>{
            override fun onClick(model: ModelProductShort) {
                startActivity(model.createNewIntentToProductActivity(requireContext()))
            }
        })

        binding.pagerImages.adapter = imagesAdapter
        binding.recRecommendation.apply {
            adapter = recommendationAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        binding.topTitleScreen.setOnClickListener {
            requireActivity().finish()
        }

        binding.pagerImages.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.dots.selectTab(position)
            }
        })

        binding.dots.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.pagerImages.setCurrentItem(tab!!.position, true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

        model.apply {
            binding.productDescription.text = getNormalDescription()
            binding.productPrice.text = getPriceWithCurrency()
            imagesAdapter.setData(images)
            binding.dots.createDots(model.images.size)
            binding.topTitleScreen.text = name
        }

        fillRecommendation()
    }

    private fun fillRecommendation(){
        Connection.api.getRecommendation(model.id).push(object: ConnectionController.OnGetData<ResponseRecommendationProducts>{
            override fun onGetData(model: ResponseRecommendationProducts) {
                recommendationAdapter.setData(model.data)
            }

            override fun onError(error: String) {
                Log.e(TAG, error)
            }
        })
    }

    override fun onGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater {
        return super.onGetLayoutInflater(savedInstanceState).cloneInContext(ContextThemeWrapper(requireContext(), theme))
    }
}