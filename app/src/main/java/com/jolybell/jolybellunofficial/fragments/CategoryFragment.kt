package com.jolybell.jolybellunofficial.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.jolybell.jolybellunofficial.adapters.MutableAdapter
import com.jolybell.jolybellunofficial.adapters.ProductsAdapter
import com.jolybell.jolybellunofficial.databinding.LayoutCategoryBinding
import com.jolybell.jolybellunofficial.models.ModelCategory
import com.jolybell.jolybellunofficial.models.ModelProductShort
import com.jolybell.jolybellunofficial.models.request.QueryFilter.Companion.createQueryFiltersGetProductsCategory
import com.jolybell.jolybellunofficial.models.response.ResponseProducts
import com.jolybell.jolybellunofficial.сommon.utils.VersionHelper.Companion.getSerializableVersion
import com.jolybell.jolybellunofficial.сommon.network.Connection
import com.jolybell.jolybellunofficial.сommon.network.ConnectionController
import com.jolybell.jolybellunofficial.сommon.network.ConnectionController.Companion.push

class CategoryFragment(
    fragmentControl: ReplacementFragment.FragmentControl
) : ReplacementFragmentItem(fragmentControl) {

    private lateinit var binding: LayoutCategoryBinding
    private lateinit var adapter: ProductsAdapter
    private lateinit var model: ModelCategory

    companion object {
        const val TAG = "CategoryFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutCategoryBinding.inflate(inflater, container, false)
        model = requireArguments().getSerializableVersion("model", ModelCategory::class.java)!!
        adapter = ProductsAdapter(object: MutableAdapter.OnClick<ModelProductShort>{
            override fun onClick(model: ModelProductShort) {
                startActivity(model.createNewIntentToProductActivity(requireContext()))
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.topContent.setOnClickListener {
            fragmentControl.finishCurrent()
        }

        binding.topContent.text = model.name

        binding.recProducts.apply {
            adapter = this@CategoryFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        Connection.api.getProducts(
            filters = createQueryFiltersGetProductsCategory(model.alias),
        ).push(object: ConnectionController.OnGetData<ResponseProducts>{
            override fun onGetData(model: ResponseProducts) {
                adapter.setData(model.data)
            }

            override fun onError(error: String) {
                Log.e(TAG, error)
            }

        })
    }
}