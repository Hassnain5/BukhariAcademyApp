package com.hasnain.application.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hasnain.application.fragments.HomeFragment
import com.hasnain.application.fragments.AccountFragment
import com.hasnain.application.fragments.AboutFragment

class FragmentAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
): FragmentStateAdapter(fragmentManager, lifecycle){
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int) : Fragment {
        return when (position) {
            0 -> HomeFragment()

            1 -> AccountFragment()
            else -> AboutFragment()
        }

    }
}