package com.jolybell.jolybellunofficial.screens

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import androidx.fragment.app.FragmentTransaction
import com.jolybell.jolybellunofficial.R
import com.jolybell.jolybellunofficial.databinding.ActivityProductBinding
import com.jolybell.jolybellunofficial.dialogs.AlertMessageDialog
import com.jolybell.jolybellunofficial.dialogs.AlertMessageDialog.Companion.getInstanceForError
import com.jolybell.jolybellunofficial.fragments.ProductFragment
import com.jolybell.jolybellunofficial.models.ModelProduct
import com.jolybell.jolybellunofficial.models.response.ResponseProduct
import com.jolybell.jolybellunofficial.сommon.network.Connection
import com.jolybell.jolybellunofficial.сommon.network.ConnectionController
import com.jolybell.jolybellunofficial.сommon.network.ConnectionController.Companion.push
import com.jolybell.jolybellunofficial.сommon.utils.ColorUtils.Companion.getAverageRGB
import com.jolybell.jolybellunofficial.сommon.utils.ColorUtils.Companion.isLight
import com.jolybell.jolybellunofficial.сommon.utils.ImageUtils.Companion.bitmapFromUrl

class ProductActivity : AppCompatActivity() {

    private var id: String = "-1"
    private lateinit var binding: ActivityProductBinding
    companion object {
        const val TAG = "ProductActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.extras!!.getString("id")!!

        prepare()
    }

    private fun prepare(){
        Connection.api.getProduct(id).push(object: ConnectionController.OnGetData<ResponseProduct>{
            override fun onGetData(model: ResponseProduct) {
                createProductFragment(model.data)
            }

            override fun onError(error: String) {
                this@ProductActivity.getInstanceForError(error).show()
            }

        })
    }

    private fun setNewProductFragment(model: ModelProduct, themeRes: Int){
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.frame_product, ProductFragment(model, themeRes))
            .commit()
    }

    private fun createProductFragment(modelProduct: ModelProduct){
        bitmapFromUrl(this@ProductActivity, Connection.URLS.getUrlImage(modelProduct.images[0].alias), object: ConnectionController.OnGetData<Bitmap>{
            override fun onGetData(model: Bitmap) {
                val isLight = model.getAverageRGB().isLight()
                setNewProductFragment(modelProduct, if (isLight) R.style.ProductActivityLight else R.style.ProductActivityDark)
            }

            override fun onError(error: String) {}
        })
    }
}