package com.jolybell.jolybellunofficial.screens

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.jolybell.jolybellunofficial.adapters.ImagesAdapter
import com.jolybell.jolybellunofficial.databinding.ActivityProductBinding
import com.jolybell.jolybellunofficial.models.ModelProduct
import com.jolybell.jolybellunofficial.models.response.ResponseProduct
import com.jolybell.jolybellunofficial.сommon.network.Connection
import com.jolybell.jolybellunofficial.сommon.network.ConnectionController
import com.jolybell.jolybellunofficial.сommon.network.ConnectionController.Companion.push
import com.jolybell.jolybellunofficial.сommon.utils.ColorUtils.Companion.getAverageColor
import com.jolybell.jolybellunofficial.сommon.utils.ColorUtils.Companion.isLight
import com.jolybell.jolybellunofficial.сommon.utils.ImageUtils.Companion.bitmapFromUrl

class ProductActivity : AppCompatActivity() {

    private var id: String = "-1"
    private lateinit var model: ModelProduct
    private lateinit var imagesAdapter: ImagesAdapter
    private lateinit var binding: ActivityProductBinding

    companion object {
        const val TAG = "ProductActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.extras!!.getString("id")!!
        imagesAdapter = ImagesAdapter()
        binding.pagerImages.adapter = imagesAdapter

        binding.topTitleScreen.setOnClickListener {
            finish()
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

        fillContent()
    }

    private fun refreshContent(){
        model.apply {
            binding.productDescription.text = getNormalDescription()
            binding.productPrice.text = getPriceWithCurrency()
            imagesAdapter.setData(images)
            binding.dots.createDots(model.images.size)
            binding.topTitleScreen.text = name

            bitmapFromUrl(this@ProductActivity, Connection.URLS.getUrlImage(images[0].alias), object: ConnectionController.OnGetData<Bitmap>{
                override fun onGetData(model: Bitmap) {
                    Log.e("color", model.getAverageColor().isLight().toString())
                }

                override fun onError(error: String) {}
            })

        }
    }

    private fun fillContent(){
        Connection.api.getProduct(id).push(object: ConnectionController.OnGetData<ResponseProduct>{
            override fun onGetData(model: ResponseProduct) {
                this@ProductActivity.model = model.data
                refreshContent()
            }

            override fun onError(error: String) {
                Log.e(TAG, error)
            }

        })
    }
}