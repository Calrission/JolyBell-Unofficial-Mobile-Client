package com.jolybell.jolybellunofficial.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jolybell.jolybellunofficial.adapters.ProductsAdapter
import com.jolybell.jolybellunofficial.databinding.ActivityProductBinding
import com.jolybell.jolybellunofficial.models.ModelProduct
import com.jolybell.jolybellunofficial.models.response.ResponseProduct
import com.jolybell.jolybellunofficial.сommon.network.Connection
import com.jolybell.jolybellunofficial.сommon.network.ConnectionController
import com.jolybell.jolybellunofficial.сommon.network.ConnectionController.Companion.push
import com.jolybell.jolybellunofficial.сommon.network.HeadersData

class ProductActivity : AppCompatActivity() {

    private var id: String = "-1"
    private lateinit var model: ModelProduct
    private lateinit var binding: ActivityProductBinding

    companion object {
        const val TAG = "ProductActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.extras!!.getString("id")!!

        binding.back.setOnClickListener {
            finish()
        }

        fillContent()
    }

    private fun refreshContent(){
        model.apply {
            binding.productTitle.text = name
            binding.productDescription.text = getNormalDescription()
            binding.productPrice.text = "${getPrice()} ${HeadersData.currency}"
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