package com.example.chucknorrisjokes.ui.category.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.chucknorrisjokes.R
import kotlinx.android.synthetic.main.item_category.view.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CategoryAdapter(private val categoryList: List<String>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    var onClickLiveData: MutableLiveData<String> = MutableLiveData()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)

        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.categoryTextView.apply {
            text = category
            setOnClickListener {
                onClickLiveData.postValue(category)
            }
        }
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryTextView: TextView = itemView.categoryTextView
    }
}