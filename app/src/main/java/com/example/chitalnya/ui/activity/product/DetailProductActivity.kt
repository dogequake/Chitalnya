package com.example.chitalnya.ui.activity.product

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.lifecycle.ViewModelProvider
import com.example.chitalnya.data.datasource.ServiceBuilder
import com.example.chitalnya.data.interfaces.BooksInterface
import com.example.chitalnya.data.interfaces.UsersInterface
import com.example.chitalnya.data.model.ApiResponse
import com.example.chitalnya.data.model.Book
import com.example.chitalnya.data.model.Resource
import com.example.chitalnya.data.model.User
import com.example.chitalnya.databinding.ActivityDetailProductBinding
import com.example.chitalnya.ui.activity.main.MainActivity
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailProductActivity : AppCompatActivity() {
    companion object {
        const val PRODUCT_ID_KEY = "ProductId"
    }
    private lateinit var binding: ActivityDetailProductBinding
    private lateinit var viewModel: DetailProductViewModel
    private var resultQuery : Boolean? = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(DetailProductViewModel::class.java)
        viewModel.productDetails.observe(this) {
            onProductDetailsLoaded(it)
        }

        binding.backBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //binding.deleteProductBtn.setOnClickListener{
        //    onDeleteProduct()
        //}

//        binding.editProductBtn.setOnClickListener{
//            val id = intent.getIntExtra(PRODUCT_ID_KEY, 0)
//            val intent = Intent(this, EditProductActivity::class.java)
//            intent.putExtra(PRODUCT_ID_KEY, id)
//            startActivity(intent)
//            finish()
//        }
//        val editProduct : Button = findViewById(R.id.editProductBtn)
//        val nameEdit : EditText = findViewById(R.id.nameEditText)
//        val surnameEdit : EditText = findViewById(R.id.surnameEditText)
//        val loginLayout : EditText = findViewById(R.id.loginEditText)
//        val passwordLayout : EditText = findViewById(R.id.passwordEditText)
//        val doublePasswordLayout : EditText = findViewById(R.id.doublePasswordEditText)

//        registration.setOnClickListener{
//            val login = loginLayout.text.toString().trim()
//            val password = passwordLayout.text.toString().trim()
//            val doublePassword = doublePasswordLayout.text.toString().trim()
//            val Name = nameEdit.text.toString().trim()
//            val Surname = surnameEdit.text.toString().trim()
//            reg(login, password, Name, Surname, doublePassword = password)
//        }
    }

    private fun onDeleteProduct() {
        val result = ServiceBuilder.buildService(BooksInterface::class.java)
        val id = intent.getIntExtra(PRODUCT_ID_KEY, 0)
        result.deleteBook(id).enqueue(object : Callback<Book>{
            override fun onResponse(call: Call<Book>, response: Response<Book>) {
                if (response.isSuccessful){
                    val intent = Intent(this@DetailProductActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<Book>, t: Throwable) {
                Log.e("no", t.message.toString())
            }

        })
    }

    private fun onProductDetailsLoaded(res: Resource<Book>) {
        when (res) {
            is Resource.Success -> {
                displayLoading(false)
                displayProductDetails(res.data)
            }
            is Resource.Loading -> {
                displayLoading(false)
            }
            is Resource.Error -> {
                // TODO: display error
            }
        }
    }
    private fun displayLoading(isLoadingVisible: Boolean) {
        //binding.prgBarMovies.isGone = !isLoadingVisible
        binding.detailConstraintLayout.isGone = isLoadingVisible
    }
    private fun displayProductDetails(product: Book) {
        binding.productTitle.text = product.title
        binding.productDescription.text = product.description
        binding.productAuthor.text = product.author
//        binding.productCountPage.text = product.countPage.toString() + " страниц"
//        binding.productCopyright.text = product.copyright
//        binding.productPublisher.text = product.publisher
//        binding.productTranslator.text = product.translator
        Picasso.get().load(product.bookpic).into(binding.imgProduct)
    }

}