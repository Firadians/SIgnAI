package com.example.signai.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.signai.MenuActivity
import com.example.signai.auth.loginActivity
import com.example.signai.auth.registerActivity
import com.example.signai.databinding.ActivityAppOpeningBinding

class appOpeningActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppOpeningBinding

    //Created by Jaydeep Motisariya
    //Date 16th Oct 2021

    private val data = mutableListOf<String>()
    private val fragmentList = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppOpeningBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        @Suppress("DEPRECATION")
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        addToList()
        fragmentList.add(firstOnboardFragment())
        fragmentList.add(secondOnboardFragment())
        fragmentList.add(thirdOnboardFragment())

        binding.vpOnBoardSlider.adapter = onboardingSliderAdapter(this, fragmentList)
        binding.vpOnBoardSlider.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.indicatorLayout.setViewPager(binding.vpOnBoardSlider)

        binding.buttonSignIn.setOnClickListener {
            startActivity(Intent(this@appOpeningActivity, loginActivity::class.java))
            finish()
        }
        binding.buttonSignUp.setOnClickListener {
            startActivity(Intent(this@appOpeningActivity, registerActivity::class.java))
            finish()
        }
//        binding.btnNext.setOnClickListener {
//            viewPager.apply {
//                beginFakeDrag()
//                fakeDragBy(-10f)
//                endFakeDrag()
//            }
//        }
//
//        binding.btnBack.setOnClickListener {
//            viewPager.apply {
//                beginFakeDrag()
//                fakeDragBy(10f)
//                endFakeDrag()
//            }
//        }

    }

    private fun addToList() {
        for (item in 1..3) {
            data.add("item $item")
        }
    }



}