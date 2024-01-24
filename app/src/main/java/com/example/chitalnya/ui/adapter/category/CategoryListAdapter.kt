package com.example.chitalnya.ui.adapter.category

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chitalnya.data.model.Article
import com.example.chitalnya.data.model.Category
import com.example.chitalnya.databinding.ItemArticleCardBinding
import com.example.chitalnya.databinding.ItemCategoryCardBinding
import com.example.chitalnya.ui.activity.main.MainActivity
import com.example.chitalnya.ui.adapter.article.ArticleItemCallBack
import com.example.chitalnya.ui.adapter.base.BaseListAdapter
import com.squareup.picasso.Picasso

class CategoryListAdapter(context: MainActivity) :
    BaseListAdapter<Category, CategoryListAdapter.ViewHolder>(context,
        CategoryItemCallBack()
    ) {
    class ViewHolder(val binding: ItemCategoryCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = getItem(position)
        val binding = holder.binding

        binding.root.setOnClickListener {
            callOnItemClickListener(position, product, binding.root)
        }
        binding.categoryNameBtn.text = product.nameCategory
    }
}