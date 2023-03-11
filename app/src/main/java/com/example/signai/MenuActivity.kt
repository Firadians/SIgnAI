package com.example.signai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.signai.databinding.ActivityMenuBinding
import com.example.signai.databinding.ActivityRegisterBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MenuActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setBackground(null)
    }
}