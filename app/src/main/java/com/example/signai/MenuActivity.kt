package com.example.signai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        supportActionBar?.hide()
        val fragOne = HomeFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_container, fragOne)
            commit()
        }
    }
}