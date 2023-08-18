package com.gracie.billz.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.gracie.billz.databinding.ActivityMainBinding
import com.gracie.billz.model.RegisterRequest
import com.gracie.billz.utils.Constants
import com.gracie.billz.viewModel.UserViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val userViewModel : UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        redirectUser()

        binding.btLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btRegister.setOnClickListener {
            validateForm()
            clearErrors()
        }

        userViewModel.errLiveData.observe(this, Observer { err ->
            Toast.makeText(this, err, Toast.LENGTH_LONG).show()
            binding.pbRegister.visibility = View.GONE
        })

        userViewModel.regLiveData.observe(this, Observer { regResponse ->
            binding.pbRegister.visibility = View.GONE
            Toast.makeText(this, regResponse.message, Toast.LENGTH_LONG).show()
            startActivity(Intent(this, LoginActivity::class.java))
        })
    }

    private fun validateForm(): Boolean {
        val firstname = binding.etFirstName.text.toString()
        val lastName = binding.etEmail2.text.toString()
        val phoneNumber = binding.etPhoneNumber.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val confirm = binding.etConfirm.text.toString()
        var error = false

        if (firstname.isEmpty()) {
            error = true
            binding.etFirstName.error = "First Name is required"
            return false
        }
        if (lastName.isEmpty()) {
            error = true
            binding.etEmail2.error = "Last name is required"
            return false
        }

        if (phoneNumber.isEmpty()) {
            error = true
            binding.etPhoneNumber.error = "Phone number is required"
            return false
        }

        if (email.isEmpty()) {
            error = true
            binding.etEmail.error = "Email address is required"
            return false
        }

        if (password != confirm) {
            error = true
            binding.etPassword.error = "Password is required"
            return false
        }
        if (password != confirm) {
            error = true
            binding.etPassword.error = "Password does not match"
            return false
        }
        if (!error) {
            val registerRequest = RegisterRequest(
                firstName = firstname,
                lastName = lastName,
                phoneNumber = phoneNumber,
                email = email,
                password = password
            )
            binding.pbRegister.visibility = View.VISIBLE
            userViewModel.registerUser(registerRequest)
        }
        return true
    }

    private fun clearErrors() {
        binding.tilFirstName.error = null
        binding.tilPhoneNumber.error = null
        binding.tilEmail.error = null
        binding.tilPassword.error = null
        binding.tilConfirm.error = null
    }

    fun redirectUser(){
        val sharedPrefs = getSharedPreferences(Constants.PREFS , Context.MODE_PRIVATE )
        var userId = sharedPrefs.(Constants.USER_ID , Constants.EMPTY_STRING) ?: Constants.EMPTY_STRING
            startActivity(Intent(this, HomeActivity:: class.java))
        }



    }

