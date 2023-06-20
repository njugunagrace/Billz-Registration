package com.gracie.billz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.gracie.billz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val registerButton: Button = binding.btRegister
        registerButton.setOnClickListener {
            if (validateForm()) {
                val username = binding.etUsername.text.toString()
                val phoneNumber = binding.etPhoneNumber.text.toString()
                val email = binding.etEmail.text.toString()
                val password = binding.etPassword.text.toString()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()

            }
        }

        val login : Button = binding.btLogin
        login.setOnClickListener{
            val intent2 = Intent(this, LoginActivity::class.java)
            startActivity(intent2)
            finish()
        }
    }

    private fun validateForm(): Boolean {
        val username = binding.etUsername.text.toString()
        val phoneNumber = binding.etPhoneNumber.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (username.isEmpty()) {
            binding.etUsername.error = "Username is required"
            return false
        }

        if (phoneNumber.isEmpty()) {
            binding.etPhoneNumber.error = "Phone number is required"
            return false
        }

        if (email.isEmpty()) {
            binding.etEmail.error = "Email address is required"
            return false
        }

        if (password.isEmpty()) {
            binding.etPassword.error = "Password is required"
            return false
        }

        return true
    }
}



