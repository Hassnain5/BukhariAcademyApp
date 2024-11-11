package com.hasnain.application

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.hasnain.application.fragments.WelcomeFragment
import com.hasnain.application.fragments.GetStartedFragment
import com.hasnain.application.databinding.ActivityGetStartedScreenBinding


class GetStartedScreen : AppCompatActivity() {
private lateinit var binding: ActivityGetStartedScreenBinding
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityGetStartedScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewPager = binding.viewPager
        viewPager.adapter = ScreenSlidePagerAdapter(this)
    }

    private inner class ScreenSlidePagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> WelcomeFragment()
                1 -> GetStartedFragment()
                else -> throw IllegalArgumentException("Invalid position")
            }
        }
    }
}
