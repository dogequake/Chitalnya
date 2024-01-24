package com.example.chitalnya.ui.adapter.category

import androidx.recyclerview.widget.DiffUtil
import com.example.chitalnya.data.model.Category

class CategoryItemCallBack : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem:
    Category
    ): Boolean =
        oldItem.idCategory == newItem.idCategory
    override fun areContentsTheSame(oldItem: Category, newItem:
    Category
    ): Boolean =
        oldItem == newItem
}