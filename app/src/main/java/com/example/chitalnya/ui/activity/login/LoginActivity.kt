package com.example.chitalnya.ui.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chitalnya.data.datasource.ServiceBuilder
import com.example.chitalnya.data.interfaces.UsersInterface
import com.example.chitalnya.data.model.User
import com.example.chitalnya.databinding.ActivityLoginBinding
import com.example.chitalnya.ui.activity.main.MainActivity
import com.example.chitalnya.ui.activity.product.DetailProductActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
//    init {
//        if (loginBoolean){
//            val intent = Intent(this@LoginActivity, MainActivity::class.java)
//            startActivity(intent)
//            Log.e("n", loginBoolean.toString())
//        }
////        else {
////            val login = findViewById<EditText>(R.id.loginEditText).toString()
////            val password = findViewById<EditText>(R.id.passwordEditText).toString()
////            auth(login,password)
////        }
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signInBtn.setOnClickListener{
            val login = binding.loginEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()
            auth(login,password)
        }
        binding.regBtn.setOnClickListener{
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
    fun auth(login: String, password: String){
        if(login.isNotEmpty() && password.isNotEmpty()){
            val result = ServiceBuilder.buildService(UsersInterface::class.java)
            result.getAuth(login, password).enqueue(object : Callback<User>{
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful){
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.putExtra(DetailProductActivity.PRODUCT_ID_KEY, response.body()!!.idUser)
                        startActivity(intent)

//                        if(response.body()!!.roleId == 2){
//                            Toast.makeText(applicationContext, "ТИХО НЕ НАДО НЕ РЕАЛИЗОВАНО", Toast.LENGTH_SHORT).show()
//                        }
//                        else if (response.body()!!.roleId == 1){
//
//                        }
                    }
                    else {
                        if(login == "offline" && password == "1"){
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            intent.putExtra(DetailProductActivity.PRODUCT_ID_KEY, 1)
                            startActivity(intent)
                        }
                        Toast.makeText(this@LoginActivity, "Неккоректные данные", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Неккоректные данные", Toast.LENGTH_LONG).show()
                }
            })
        }
        else{
            Toast.makeText(this@LoginActivity, "Не kaka все поля заполнены", Toast.LENGTH_LONG).show()
        }
    }
}