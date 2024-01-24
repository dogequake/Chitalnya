package com.example.chitalnya.ui.adapter.base

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chitalnya.ui.activity.main.MainActivity

abstract class BaseListAdapter<T, VH :
RecyclerView.ViewHolder>(
    val context: MainActivity,
    itemCallback: ItemCallback<T>
) : ListAdapter<T, VH>(itemCallback) {
    val inflater = LayoutInflater.from(context)
    private var onItemClickListener: OnItemClickListener<T>? =
        null
    fun setOnItemClickListener(listener: OnItemClickListener<T>) {
        onItemClickListener = listener
    }
    protected fun callOnItemClickListener(position: Int, item: T,
                                          view: View) {
        onItemClickListener?.onItemClick(position, item, view)
    }
}