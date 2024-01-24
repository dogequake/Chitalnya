package com.example.chitalnya.ui.activity.account

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.chitalnya.data.datasource.ServiceBuilder
import com.example.chitalnya.data.interfaces.UsersInterface
import com.example.chitalnya.data.model.Resource
import com.example.chitalnya.data.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _informationList = MutableLiveData<Resource<User>>(Resource.Loading)

    val informationList: LiveData<Resource<User>> = _informationList

    init {
        val userId: Int = savedStateHandle[AccountActivity.USER_ID]!!
        loadUserById(userId)
    }

    private fun loadUserById(userId: Int) {
        val retrofit = ServiceBuilder.buildService(UsersInterface::class.java)

        _informationList.value = Resource.Loading

        retrofit.getUserById(userId).enqueue(object : Callback<User> {
            override fun onResponse(
                call: Call<User>,
                response: Response<User>) {
                try {
                    if (response.isSuccessful) {
                        val responseBody = response.body()!!
                        // отправка данных в ui
                        _informationList.value = Resource.Success(responseBody)
                    }
                } catch (e: Throwable) {
                    e.printStackTrace()
                    // сохранение ошибок и отправка в ui
                    _informationList.value = Resource.Error(e)
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("Failed", "Api Failad" + t.message)
                // охранение ошибок и отправка в ui
                _informationList.value = Resource.Error(t)
            }
        })
    }
}