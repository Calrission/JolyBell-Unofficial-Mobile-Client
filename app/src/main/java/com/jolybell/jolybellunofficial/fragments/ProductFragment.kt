package com.jolybell.jolybellunofficial.fragments

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.jolybell.jolybellunofficial.adapters.ImagesAdapter
import com.jolybell.jolybellunofficial.adapters.MutableAdapter
import com.jolybell.jolybellunofficial.adapters.RecommendationsAdapter
import com.jolybell.jolybellunofficial.databinding.FragmentLayoutProductBinding
import com.jolybell.jolybellunofficial.dialogs.AlertMessageDialog.Companion.getAlertMessageDialogForError
import com.jolybell.jolybellunofficial.dialogs.MessageDialog
import com.jolybell.jolybellunofficial.dialogs.TableMessageDialog
import com.jolybell.jolybellunofficial.models.ModelProduct
import com.jolybell.jolybellunofficial.models.ModelProductShort
import com.jolybell.jolybellunofficial.models.ModelSize
import com.jolybell.jolybellunofficial.models.response.ResponseRecommendationProducts
import com.jolybell.jolybellunofficial.views.SizesProductView
import com.jolybell.jolybellunofficial.сommon.network.Connection
import com.jolybell.jolybellunofficial.сommon.network.ConnectionController
import com.jolybell.jolybellunofficial.сommon.network.ConnectionController.Companion.push
import com.jolybell.jolybellunofficial.сommon.utils.DoubleUtils.Companion.withCurrency

class ProductFragment(private val model: ModelProduct, private val theme: Int): Fragment() {

    lateinit var binding: FragmentLayoutProductBinding
    private lateinit var imagesAdapter: ImagesAdapter
    private lateinit var recommendationAdapter: RecommendationsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLayoutProductBinding.inflate(inflater, container, false)
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

        binding.dots.addOnTabSelectedListener(object: OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.pagerImages.setCurrentItem(tab!!.position, true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

        binding.sizes.addOnSizeSelect(object: SizesProductView.OnSizeSelected{
            override fun onSelect(model: ModelSize) {
                binding.productPrice.text = model.pivot.price.withCurrency()
            }

            override fun onUnselect(model: ModelSize) {}

            override fun onReselect(model: ModelSize) {}

        })

        if (model.description_sizes != null)
            binding.productBtn1.setOnClickListener{
                TableMessageDialog.getInstance(requireContext(), model).show()
            }
        else
            binding.productBtn1.visibility = View.GONE



        if (model.description_care != null){
            binding.productBtn2.setOnClickListener {
                val modelMessage = model.getCareModelMessage()
                MessageDialog(requireContext(), modelMessage!!).show()
            }
        }else{
            binding.productBtn2.visibility = View.GONE
        }


        model.apply {
            binding.productDescription.text = getNormalDescription()
            binding.productPrice.text = price.withCurrency()
            imagesAdapter.setData(images)
            binding.dots.createDots(model.images.size)
            binding.topTitleScreen.text = name
            binding.sizes.addSizes(sizes)
        }

        loadRecommendation()
    }

    private fun loadRecommendation(){
        Connection.api.getRecommendation(model.id).push(object: ConnectionController.OnGetData<ResponseRecommendationProducts>{
            override fun onGetData(model: ResponseRecommendationProducts) {
                if (model.data.isNotEmpty()) {
                    binding.sloganRecommendation.visibility = View.VISIBLE
                    recommendationAdapter.setData(model.data)
                }else
                    binding.sloganRecommendation.visibility = View.GONE
            }

            override fun onError(error: String) {
                requireContext().getAlertMessageDialogForError(error).show()
            }
        })
    }

    override fun onGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater {
        return super.onGetLayoutInflater(savedInstanceState).cloneInContext(ContextThemeWrapper(requireContext(), theme))
    }
}