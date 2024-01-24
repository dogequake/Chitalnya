package com.example.chitalnya.ui.activity.product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.chitalnya.data.datasource.ServiceBuilder
import com.example.chitalnya.data.interfaces.BooksInterface
import com.example.chitalnya.data.model.Book
import com.example.chitalnya.data.model.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailProductViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _productDetails = MutableLiveData<Resource<Book>>(Resource.Loading)

    val productDetails: LiveData<Resource<Book>> = _productDetails

    init {
        val bookId: Int = savedStateHandle[DetailProductActivity.PRODUCT_ID_KEY]!!
        loadProductById(bookId)
    }
    private fun loadProductById(bookId: Int) {
        val retrofit = ServiceBuilder.buildService(BooksInterface::class.java)

        _productDetails.value = Resource.Loading

        retrofit.getBooksById(bookId).enqueue(object : Callback<Book> {
            override fun onResponse(call: Call<Book>, response: Response<Book>) {
                try {
                    if (response.isSuccessful) {
                        val responseBody = response.body()!!
                        // отправка данных в ui
                        _productDetails.value = Resource.Success(responseBody)
                    }
                } catch (e: Throwable) {
                    e.printStackTrace()
                    // сохранение ошибок и отправка в ui
                    _productDetails.value = Resource.Error(e)
                }
            }
            override fun onFailure(call: Call<Book>, t: Throwable) {
                Log.e("Failed", "Api Failad" + t.message)
                // охранение ошибок и отправка в ui
                _productDetails.value = Resource.Error(t)
            }
        })
    }
}