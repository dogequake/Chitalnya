package com.example.chitalnya.ui.adapter.article

import androidx.recyclerview.widget.DiffUtil
import com.example.chitalnya.data.model.Article

class ArticleItemCallBack : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem:
    Article
    ): Boolean =
        oldItem.idArticle == newItem.idArticle
    override fun areContentsTheSame(oldItem: Article, newItem:
    Article
    ): Boolean =
        oldItem == newItem
}