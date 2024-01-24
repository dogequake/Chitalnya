package com.example.chitalnya.ui.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chitalnya.data.model.Article
import com.example.chitalnya.data.model.Book
import com.example.chitalnya.data.model.Category
import com.example.chitalnya.databinding.ActivityMainBinding
import com.example.chitalnya.ui.activity.product.CreateProductActivity
import com.example.chitalnya.ui.activity.product.DetailProductActivity
import com.example.chitalnya.ui.adapter.article.ArticleListAdapter
import com.example.chitalnya.ui.adapter.category.CategoryListAdapter
import com.example.chitalnya.ui.adapter.category.SpaceItemDecoration
import com.example.chitalnya.ui.adapter.product.ProductListAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var productsAdapter: ProductListAdapter
    private lateinit var articleAdapter: ArticleListAdapter
    private lateinit var categoryAdapter: CategoryListAdapter

    private fun navigateToProductDetails(product: Book) {
        val intent = Intent(this,DetailProductActivity::class.java)
        intent.putExtra(DetailProductActivity.PRODUCT_ID_KEY, product.idBook)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //books start
        productsAdapter = createProductsAdapter()
        binding.productRecyclerView.adapter = productsAdapter
        viewModel.productList.observe(this) { onProductsLoaded(it) }
        productsAdapter.setOnItemClickListener {_, item, _ ->
            navigateToProductDetails(item)
        }
//        binding.createProductBtn.setOnClickListener{
//            onCreateProduct()
//        }
        //books end
        //article start
        articleAdapter = createArticleAdapter()
        binding.articleRecyclerView.adapter = articleAdapter
        viewModel.articleList.observe(this) { onArticleLoaded(it) }
        //article end
        //category start
        categoryAdapter = createCategoryAdapter()
        binding.categoryRecyclerView.adapter = categoryAdapter
        binding.categoryRecyclerView.addItemDecoration(SpaceItemDecoration(25))
        binding.categoryRecyclerView.layoutManager = GridLayoutManager(this, 2)
        //добавляет между объектами отступы
        viewModel.categoryList.observe(this) { onCatergoryLoaded(it) }
        //category end
    }
    private fun onCreateProduct(){
        val intent = Intent(this,CreateProductActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun createProductsAdapter(): ProductListAdapter {
        val adapter = ProductListAdapter(this)
        //передаем существующие данные в адаптер
        adapter.submitList(viewModel.productList.value)
        return adapter
    }
    private fun onProductsLoaded(products: List<Book>) {
        // передаем загруженные данные в адаптер
        productsAdapter.submitList(products)
    }

    private fun createArticleAdapter(): ArticleListAdapter {
        val adapter = ArticleListAdapter(this)
        adapter.submitList(viewModel.articleList.value)
        return adapter
    }
    private fun onArticleLoaded(products: List<Article>) {
        articleAdapter.submitList(products)
    }

    private fun createCategoryAdapter(): CategoryListAdapter {
        val adapter = CategoryListAdapter(this)
        adapter.submitList(viewModel.categoryList.value)
        return adapter
    }
    private fun onCatergoryLoaded(products: List<Category>) {
        categoryAdapter.submitList(products)
    }
}