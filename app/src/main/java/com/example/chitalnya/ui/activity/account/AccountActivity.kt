package com.example.chitalnya.ui.activity.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import androidx.lifecycle.ViewModelProvider
import com.example.chitalnya.R
import com.example.chitalnya.data.model.Resource
import com.example.chitalnya.data.model.User
import com.example.chitalnya.databinding.ActivityAccountBinding
import com.squareup.picasso.Picasso

class AccountActivity : AppCompatActivity() {
    companion object {
        const val USER_ID = "userId"
    }

    private lateinit var binding: ActivityAccountBinding
    private lateinit var viewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        viewModel.informationList.observe(this) {
            onUserLoaded(it)
        }
    }
    private fun onUserLoaded(res: Resource<User>) {
        when (res) {
            is Resource.Success -> {
                displayProductDetails(res.data)
            }

            is Resource.Error -> TODO()
            Resource.Loading -> TODO()
        }
    }
    private fun displayProductDetails(user: User) {
        binding.surnameText.text = user.surname
        binding.nameText.text = user.name
        Picasso.get().load(user.userpic).into(binding.userImage)
    }
}