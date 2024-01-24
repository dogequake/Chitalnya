package com.example.chitalnya.ui.activity.login

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.chitalnya.R
import com.example.chitalnya.data.datasource.ServiceBuilder
import com.example.chitalnya.data.interfaces.BooksInterface
import com.example.chitalnya.data.interfaces.UsersInterface
import com.example.chitalnya.data.model.ApiResponse
import com.example.chitalnya.data.model.Book
import com.example.chitalnya.data.model.User
import com.example.chitalnya.databinding.ActivityAccountBinding
import com.example.chitalnya.databinding.ActivityMainBinding
import com.example.chitalnya.databinding.ActivityRegistrationBinding
import com.example.chitalnya.ui.activity.main.MainActivity
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registrationBtn.setOnClickListener{
            reg()
        }

    }
    fun reg(){
        val Login = binding.loginEditText.text
        val Password = binding.passwordEditText.text
        val DoublePassword = binding.doublePasswordEditText.text
        val Surname = binding.surnameEditText.text
        val Name = binding.nameEditText.text

        if (Password.toString().trim() == DoublePassword.toString().trim()){
            lifecycleScope.launch(Dispatchers.IO){
                val body = JsonObject().apply {
                    addProperty("login", Login.toString())
                    addProperty("password", Password.toString())
                    addProperty("surname", Surname.toString())
                    addProperty("name", Name.toString())
                }
                val result = ServiceBuilder.buildService(UsersInterface::class.java)
                result.createUser(body).enqueue(object: Callback<User>{
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful){
                            val intent = Intent(this@RegistrationActivity, LoginActivity::class.java)
                            startActivity(intent)
                        }
                        else {
                            Toast.makeText(this@RegistrationActivity, "Неккоректные данные", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Toast.makeText(this@RegistrationActivity, "Неккоректные данные", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
        else{
            Toast.makeText(this@RegistrationActivity, "Пароли не совпадают", Toast.LENGTH_LONG).show()
        }
    }
}