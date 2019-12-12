package com.example.chucknorrisjokes.ui.category.view


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.chucknorrisjokes.R
import com.example.chucknorrisjokes.ui.category.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.fragment_category.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryFragment : Fragment() {

    private val viewModel: CategoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.let { vm ->
            vm.categoryLiveData.observe(this, Observer {
/*                it?.forEach {
                    Log.d("Main", it)
                }*/
                it?.let { categoryList ->
                    initCategoryAdapter(categoryList)
                }
            })

            vm.getJokeCategories()
        }

    }

    private fun initCategoryAdapter(categoryList: List<String>) {
        val adapter = CategoryAdapter(categoryList).apply {
            onClickLiveData.observe(requireActivity(), Observer {

            })
        }

        categoryRecyclerView.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false
            )
            addItemDecoration(
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            )
        }
    }
}
