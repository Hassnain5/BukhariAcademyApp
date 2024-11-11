package com.hasnain.application

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.hasnain.application.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!hasSeenIntro()) {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, IntroSlider::class.java))
                val sharedPrefs = getSharedPreferences("intro", Context.MODE_PRIVATE)
                val editor = sharedPrefs.edit()
                editor.putBoolean("seen", true)
                editor.apply()
                finish()
            }, 4000)
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, IntroSlider::class.java))
                finish()
            }, 4000)
        }
    }

    private fun hasSeenIntro(): Boolean {
        val sharedPrefs = getSharedPreferences("intro", Context.MODE_PRIVATE)
        return sharedPrefs.getBoolean("seen", false)
    }
}
