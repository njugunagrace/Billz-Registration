package com.gracie.billz.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.gracie.billz.databinding.ActivityLoginBinding
import com.gracie.billz.model.LoginResponse
import com.gracie.billz.model.User
import com.gracie.billz.utils.Constants
import com.gracie.billz.viewModel.UserViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()
        val register: Button = binding.btReg2
        register.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }





            val loginButton: Button = binding.btLogin2

        loginButton.setOnClickListener {
//            if (validate()) {
//                val username = binding.etEmail2.text.toString()
//                val password = binding.etPassword.text.toString()

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()


//            }
        }
    }




     fun validate(): Boolean {
        val username = binding.etEmail2.text.toString()
        val password = binding.etPassword.text.toString()
         var error = false

        if (username.isEmpty()) {
            binding.etEmail2.error = "Username is required"
            return false
        }

        if (password.isEmpty()) {
            binding.etPassword.error = "Password is required"
            return false
        }
         binding.pbLogin.visibility = View.VISIBLE

        return true


         Toast.makeText(this, "Logged in succesfuully", Toast.LENGTH_LONG).show()
    }
    fun initObservers(){
        binding.pbLogin.visibility = View.GONE



    }

    fun persistLogin(loginResponse : LoginResponse){
        val sharedPrefs = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putString(Constants.USER_ID , loginResponse.user_id)
        editor.putString(Constants.ACCESS_TOKEN , loginResponse.access_token)
        //with shared preferences ensure you apply your changes
        editor.apply()

    }
}