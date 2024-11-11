package com.hasnain.application

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
//import com.hasnain.application.databinding.ActivityDashboardBinding
import com.hasnain.application.fragments.AboutFragment
import com.hasnain.application.fragments.AccountFragment
import com.hasnain.application.fragments.HomeFragment

import com.hasnain.application.databinding.ActivityDrawerPageBinding

class DrawerPage : AppCompatActivity(){
    lateinit var toggle:ActionBarDrawerToggle
    private lateinit var binding: ActivityDrawerPageBinding
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDrawerPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

}}
