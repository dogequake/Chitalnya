package com.example.chitalnya.ui.activity.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chitalnya.data.datasource.ServiceBuilder
import android.util.Log
import com.example.chitalnya.data.interfaces.ArticleInterface
import com.example.chitalnya.data.interfaces.BooksInterface
import com.example.chitalnya.data.interfaces.CategoryInterface
import com.example.chitalnya.data.model.ApiResponse
import com.example.chitalnya.data.model.Article
import com.example.chitalnya.data.model.Book
import com.example.chitalnya.data.model.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _productList = MutableLiveData<List<Book>>()
    val productList: LiveData<List<Book>> = _productList

    private val _articleList = MutableLiveData<List<Article>>()
    val articleList: LiveData<List<Article>> = _articleList

    private val _categoryList = MutableLiveData<List<Category>>()
    val categoryList: LiveData<List<Category>> = _categoryList

    init {
        loadAllProducts()
        loadAllArticles()
        loadAllCategories()
    }

    private fun loadAllProducts() {
        val retrofit = ServiceBuilder.buildService(BooksInterface::class.java)
        retrofit.getAllBooks().enqueue(object : Callback<ApiResponse<Book>> {
            override fun onResponse(
                call: Call<ApiResponse<Book>>,
                response: Response<ApiResponse<Book>>
            ) {
                try {
                    val responseBody = response.body()!!
                    _productList.value = responseBody.data
                } catch (ex: java.lang.Exception) {
                    ex.printStackTrace()
                }
            }
            override fun onFailure(call: Call<ApiResponse<Book>>, t: Throwable) {
                Log.e("Failed", "Api Failad" + t.message)
            }
        })
    }

    private fun loadAllArticles() {
        val retrofit = ServiceBuilder.buildService(ArticleInterface::class.java)
        retrofit.getArticles().enqueue(object : Callback<ApiResponse<Article>> {
            override fun onResponse(
                call: Call<ApiResponse<Article>>,
                response: Response<ApiResponse<Article>>
            ) {
                try {
                    val responseBody = response.body()!!
                    _articleList.value = responseBody.data
                } catch (ex: java.lang.Exception) {
                    ex.printStackTrace()
                }
            }
            override fun onFailure(call: Call<ApiResponse<Article>>, t: Throwable) {
                Log.e("Failed", "Api Failad" + t.message)
            }
        })
    }

    private fun loadAllCategories() {
        val retrofit = ServiceBuilder.buildService(CategoryInterface::class.java)
        retrofit.getAllCategory().enqueue(object : Callback<ApiResponse<Category>> {
            override fun onResponse(
                call: Call<ApiResponse<Category>>,
                response: Response<ApiResponse<Category>>
            ) {
                try {
                    val responseBody = response.body()!!
                    _categoryList.value = responseBody.data
                } catch (ex: java.lang.Exception) {
                    ex.printStackTrace()
                }
            }
            override fun onFailure(call: Call<ApiResponse<Category>>, t: Throwable) {
                Log.e("Failed", "Api Failad" + t.message)
            }
        })
    }
}
