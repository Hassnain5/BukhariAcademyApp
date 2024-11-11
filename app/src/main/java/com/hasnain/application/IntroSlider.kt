package com.hasnain.application

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.hasnain.application.adapters.SliderAdapter
import com.hasnain.application.databinding.ActivityIntroSliderBinding
import com.hasnain.application.SliderData

class IntroSlider : AppCompatActivity() {

    private lateinit var binding: ActivityIntroSliderBinding
    private lateinit var viewPager: ViewPager
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var sliderList: ArrayList<SliderData>
    private lateinit var skipBtn: Button
    private lateinit var indicatorSlideOneTV: TextView
    private lateinit var indicatorSlideTwoTV: TextView
    private lateinit var indicatorSlideThreeTV: TextView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroSliderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("intro_slider_pref", Context.MODE_PRIVATE)

        // Check if intro slider has been shown before
        if (sharedPreferences.getBoolean("intro_shown", false)) {
            navigateToHomepage()
            return
        }

        viewPager = binding.idViewPager
        skipBtn = binding.idBtnSkip
        indicatorSlideOneTV = binding.idTVSlideOne
        indicatorSlideTwoTV = binding.idTVSlideTwo
        indicatorSlideThreeTV = binding.idTVSlideThree

        skipBtn.setOnClickListener {
            navigateToHomepage()
        }

        sliderList = ArrayList()
        sliderList.add(
            SliderData(
                "Attendance",
                "Take attendance of students online via phone",
                R.drawable.attendence_image_intro
            )
        )
        sliderList.add(
            SliderData(
                "Manage",
                "Manage the students and teachers",
                R.drawable.manage_img_intro
            )
        )
        sliderList.add(
            SliderData(
                "Announce",
                "C++ Development Course",
                R.drawable.announce_intro
            )
        )

        sliderAdapter = SliderAdapter(this, sliderList)
        viewPager.adapter = sliderAdapter
        viewPager.addOnPageChangeListener(viewListener)
    }

    private var viewListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {
            when (position) {
                0 -> {
                    indicatorSlideTwoTV.setTextColor(resources.getColor(R.color.grey))
                    indicatorSlideThreeTV.setTextColor(resources.getColor(R.color.grey))
                    indicatorSlideOneTV.setTextColor(resources.getColor(R.color.secondary))
                }
                1 -> {
                    indicatorSlideTwoTV.setTextColor(resources.getColor(R.color.secondary))
                    indicatorSlideThreeTV.setTextColor(resources.getColor(R.color.grey))
                    indicatorSlideOneTV.setTextColor(resources.getColor(R.color.grey))
                }
                2 -> {
                    indicatorSlideTwoTV.setTextColor(resources.getColor(R.color.grey))
                    indicatorSlideThreeTV.setTextColor(resources.getColor(R.color.secondary))
                    indicatorSlideOneTV.setTextColor(resources.getColor(R.color.grey))
                }
            }
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    private fun navigateToHomepage() {
        // Mark intro slider as shown in SharedPreferences
        sharedPreferences.edit().putBoolean("intro_shown", true).apply()

        val intent = Intent(this, Homepage::class.java)
        startActivity(intent)
        finish()
    }
}
