package com.hasnain.application.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hasnain.application.R
import com.hasnain.application.fragments.AboutFragment
import com.hasnain.application.fragments.AccountFragment
import com.hasnain.application.fragments.HomeFragment
import com.hasnain.application.fragments.IntroSliderFragment
import com.hasnain.application.fragments.IntroSliderfragment2

//import kotlinx.android.synthetic.main.intro_slide_1.view.*
class IntroSliderAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2 // Number of intro slides

    override fun createFragment(position: Int) : Fragment {
        return when (position) {
            0 -> IntroSliderFragment()


            else -> IntroSliderfragment2()
        }

    }
}