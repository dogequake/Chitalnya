package com.example.chitalnya.ui.activity.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chitalnya.R
import com.example.chitalnya.data.model.Book
import com.example.chitalnya.data.model.Resource
import com.example.chitalnya.databinding.ActivityDetailProductBinding
import com.example.chitalnya.databinding.ActivityEditProductBinding
import com.squareup.picasso.Picasso

class EditProductActivity : AppCompatActivity() {
    companion object {
        const val PRODUCT_ID_KEY = "ProductId"
    }
    private lateinit var binding: ActivityEditProductBinding
    private lateinit var viewModel: DetailProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(DetailProductViewModel::class.java)
        viewModel.productDetails.observe(this) {
            onProductDetailsLoaded(it)
        }

        binding.editProductBtn.setOnClickListener{
            editProduct()
        }
    }

    private fun editProduct() {
        TODO("Not yet implemented")
    }

    private fun onProductDetailsLoaded(res: Resource<Book>) {
        when (res) {
            is Resource.Success -> {
                displayProductDetails(res.data)
            }
            is Resource.Loading -> {

            }
            is Resource.Error -> {
                // TODO: display error
            }
        }
    }
    private fun displayProductDetails(product: Book) {
        binding.titleProduct.setText(product.title)
        binding.descriptionProduct.setText(product.description)
        binding.authorProduct.setText(product.author)
        binding.countPageProduct.setText(product.countPage)
        binding.copyrightProduct.setText(product.copyright)
        binding.publisherProduct.setText(product.publisher)
        binding.translatorProduct.setText(product.translator)
        binding.imgProduct.setText(product.bookpic)
    }
}