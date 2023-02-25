package com.jolybell.jolybellunofficial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.jolybell.jolybellunofficial.adapters.CategoriesAdapter
import com.jolybell.jolybellunofficial.adapters.MutableAdapter
import com.jolybell.jolybellunofficial.databinding.FragmentLayoutListCategoriesBinding
import com.jolybell.jolybellunofficial.dialogs.AlertMessageDialog.Companion.getAlertMessageDialogForError
import com.jolybell.jolybellunofficial.models.ModelCategory
import com.jolybell.jolybellunofficial.models.response.ResponseCategories
import com.jolybell.jolybellunofficial.сommon.network.Connection
import com.jolybell.jolybellunofficial.сommon.network.ConnectionController
import com.jolybell.jolybellunofficial.сommon.network.ConnectionController.Companion.push

class ListCategoriesFragment(
    fragmentControl: ReplacementFragment.FragmentControl
): ReplacementFragmentItem(fragmentControl) {
    companion object {
        const val TAG = "GetCategories-FragmentListCategories"
    }

    private lateinit var binding: FragmentLayoutListCategoriesBinding
    private lateinit var adapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        adapter = CategoriesAdapter(onClick = object: MutableAdapter.OnClick<ModelCategory>{
            override fun onClick(model: ModelCategory) {
                fragmentControl.changeFragment(CategoryFragment::class.java, mapOf("model" to model))
            }
        })

        binding = FragmentLayoutListCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.categories.apply {
            adapter = this@ListCategoriesFragment.adapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        Connection.api.getCategories().push(object: ConnectionController.OnGetData<ResponseCategories>{
            override fun onGetData(model: ResponseCategories) {
                adapter.setData(model.data)
            }

            override fun onError(error: String) {
                requireContext().getAlertMessageDialogForError(error).show()
            }
        })
    }
}