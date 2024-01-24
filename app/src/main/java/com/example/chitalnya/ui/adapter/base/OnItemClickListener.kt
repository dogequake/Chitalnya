package com.example.chitalnya.ui.adapter.base

import android.view.View

fun interface OnItemClickListener<T> {
    fun onItemClick(position: Int, item: T, view: View)
}