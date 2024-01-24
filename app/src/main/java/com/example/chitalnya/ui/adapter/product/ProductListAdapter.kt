package com.example.chitalnya.ui.adapter.product

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chitalnya.data.model.Book
import com.example.chitalnya.databinding.ItemProductCardBinding
import com.example.chitalnya.ui.activity.main.MainActivity
import com.example.chitalnya.ui.adapter.base.BaseListAdapter
import com.squareup.picasso.Picasso

class ProductListAdapter(context: MainActivity) :
    BaseListAdapter<Book, ProductListAdapter.ViewHolder>(context,
        ProductItemCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = getItem(position)
        val binding = holder.binding

        binding.root.setOnClickListener {
            callOnItemClickListener(position, product, binding.root)
        }
//        binding.productTitle.text = product.title
//        binding.productDescription.text = product.description
        Picasso.get()
            .load(product.bookpic)
            .into(binding.productImage)
    }
    class ViewHolder(val binding: ItemProductCardBinding) :
        RecyclerView.ViewHolder(binding.root)
}
