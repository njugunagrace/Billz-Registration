package com.gracie.billz.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gracie.billz.R
import com.gracie.billz.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun onResume() {
        super.onResume()
        setUpBottomNav()
//        binding.btnLogout.setOnClickListener {
//        }
    }

    private fun setUpBottomNav(){
        binding.bnvHome.setOnItemSelectedListener {menuItem->
             when(menuItem.itemId){
                R.id.summary -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fvcHome, SummaryFragment())
                        .commit()
                    true
                }
                 R.id.upcoming -> {
                     supportFragmentManager.beginTransaction().replace(R.id.fvcHome, UpcomingBillzFragment()).commit()
                     true
                 }
                 R.id.paid -> {
                     supportFragmentManager.beginTransaction()
                         .replace(R.id.fvcHome, PaidBillzFragment()).commit()
                     true
                 }
                 R.id.settings -> {
                     supportFragmentManager.beginTransaction().replace(R.id.fvcHome, SettingsFragment()).commit()
                     true
                 }
                 else -> false
            }



        }
    }

}