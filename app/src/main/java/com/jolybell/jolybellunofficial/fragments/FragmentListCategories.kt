package com.jolybell.jolybellunofficial.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.jolybell.jolybellunofficial.adapters.CategoriesAdapter
import com.jolybell.jolybellunofficial.databinding.LayoutListCategoriesBinding
import com.jolybell.jolybellunofficial.models.ModelCategory
import com.jolybell.jolybellunofficial.models.response.ResponseCategories
import com.jolybell.jolybellunofficial.сommon.network.Connection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentListCategories(
    fragmentControl: ReplacementFragment.FragmentControl
): ReplacementFragmentItem(fragmentControl) {
    companion object {
        const val TAG = "GetCategories-FragmentListCategories"
    }

    private lateinit var binding: LayoutListCategoriesBinding
    private lateinit var adapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        adapter = CategoriesAdapter(onClick = object: CategoriesAdapter.OnClick{
            override fun onClick(model: ModelCategory) {
                fragmentControl.changeFragment(CategoryFragment::class.java, mapOf("model" to model))
            }
        })

        binding = LayoutListCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.categories.apply {
            adapter = this@FragmentListCategories.adapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        Connection.api.getCategories().enqueue(object: Callback<ResponseCategories>{
            override fun onResponse(
                call: Call<ResponseCategories>,
                response: Response<ResponseCategories>,
            ) {
                val body = response.body()
                if (body != null && body.result){
                    adapter.setData(body.data)
                }else{
                    Log.e(TAG, "Body null")
                }
            }

            override fun onFailure(call: Call<ResponseCategories>, t: Throwable) {
                Log.e(TAG, t.localizedMessage ?: t.message ?: "unknown error")
            }
        })
    }
}