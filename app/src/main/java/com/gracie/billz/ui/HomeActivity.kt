package com.gracie.billz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gracie.billz.R
import com.gracie.billz.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
    }
}