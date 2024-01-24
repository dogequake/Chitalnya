package com.example.chitalnya.ui.activity.product

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
import com.example.chitalnya.data.model.Book
import com.example.chitalnya.ui.activity.main.MainActivity
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_product)

        val list = mutableListOf<Book>()
        val button = findViewById<Button>(R.id.createProductBtn)
        button.setOnClickListener{
            createProduct()
        }


    }
    private fun createProduct() {
        val titleProduct = findViewById<EditText>(R.id.editTextTitle)
        val descriptionProduct = findViewById<EditText>(R.id.editTextDescription)
        val authorProduct = findViewById<EditText>(R.id.editTextAuthor)
        val copyrightProduct = findViewById<EditText>(R.id.editTextCopyright)
        val publisherProduct = findViewById<EditText>(R.id.editTextPublisher)
        val translatorProduct = findViewById<EditText>(R.id.editTextTranslator)
        val countpageProduct = findViewById<EditText>(R.id.editTextCountPage)
        val imageProduct = findViewById<EditText>(R.id.editTextImage)

        lifecycleScope.launch(Dispatchers.IO){
            val body = JsonObject().apply {
                addProperty("title", titleProduct.text.toString())
                addProperty("description", descriptionProduct.text.toString())
                addProperty("author", authorProduct.text.toString())
                addProperty("copyright", copyrightProduct.text.toString())
                addProperty("publisher", publisherProduct.text.toString())
                addProperty("translator", translatorProduct.text.toString())
                addProperty("countPage", countpageProduct.text.toString())
                addProperty("bookpic",imageProduct.text.toString())
            }
            val result = ServiceBuilder.buildService(BooksInterface::class.java)
            result.postBook(body).enqueue(object: Callback<Book>{
                override fun onResponse(call: Call<Book>, response: Response<Book>) {
                    Log.e("no", body.toString())
                    val intent = Intent(this@CreateProductActivity,MainActivity::class.java)
                    startActivity(intent)
                }

                override fun onFailure(call: Call<Book>, t: Throwable) {
                    Log.e("no", t.message.toString())
                }

            })
        }
    }
}