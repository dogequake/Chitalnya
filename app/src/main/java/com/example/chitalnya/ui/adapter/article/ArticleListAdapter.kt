package com.example.chitalnya.ui.adapter.article

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chitalnya.data.model.Article
import com.example.chitalnya.databinding.ItemArticleCardBinding
import com.example.chitalnya.ui.activity.main.MainActivity
import com.example.chitalnya.ui.adapter.base.BaseListAdapter
import com.squareup.picasso.Picasso

class ArticleListAdapter(context: MainActivity) :
    BaseListAdapter<Article, ArticleListAdapter.ViewHolder>(context,
        ArticleItemCallBack()
    ) {
    class ViewHolder(val binding: ItemArticleCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleListAdapter.ViewHolder {
        val binding = ItemArticleCardBinding.inflate(inflater, parent, false)
        return ArticleListAdapter.ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ArticleListAdapter.ViewHolder, position: Int) {
        val product = getItem(position)
        val binding = holder.binding

        binding.root.setOnClickListener {
            callOnItemClickListener(position, product, binding.root)
        }
        binding.titleArticle.text = product.title
        binding.descriptionArticle.text = product.description
        Picasso.get()
            .load(product.articlepic)
            .into(binding.imageArticle)
        binding.sloganArticle.text = product.slogan
    }
}