package com.example.chitalnya.ui.adapter.product

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.example.chitalnya.data.model.Book

class ProductItemCallBack : ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem:
    Book): Boolean =
        oldItem.idBook == newItem.idBook
    override fun areContentsTheSame(oldItem: Book, newItem:
    Book): Boolean =
        oldItem == newItem
}