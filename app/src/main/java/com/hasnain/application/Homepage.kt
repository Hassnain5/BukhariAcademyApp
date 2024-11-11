package com.hasnain.application

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.widget.GridView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.hasnain.application.databinding.ActivityHomepageBinding
import com.hasnain.application.fragments.AboutFragment
import com.hasnain.application.fragments.AccountFragment
import com.hasnain.application.fragments.HomeFragment


class Homepage : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityHomepageBinding
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var coursesGV: GridView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)


// drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = binding.myDrawerLayout
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)



        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        actionBar?.title = "Home"
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#153448")))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navigationView.setNavigationItemSelectedListener(this)



        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
            binding.navigationView.setCheckedItem(R.id.nav_home)
        }




//        val sharedPrefs = getSharedPreferences("loginInfo", MODE_PRIVATE)
//        val username = sharedPrefs.getString("username", "")
//        val loginstatus = sharedPrefs.getInt("loginstaus", 0)
//
//        if (loginstatus == 1 && !username.equals("")) {
//
//            binding.tvShowUsername.setText("Welcome" + username)
//            Toast.makeText(this, username, Toast.LENGTH_LONG).show()
//        } else {
//            Toast.makeText(this, "You are not logged in!", Toast.LENGTH_LONG).show()
//        }

//        binding.llTasbih.setOnClickListener() {
//
//            val i = Intent(this, TasbihCounter::class.java)
//            startActivity(i)
//
//        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var selectedFragment: Fragment? = null
        when (item.itemId) {
            R.id.nav_home -> {
                selectedFragment = HomeFragment()
            }
            R.id.nav_profile -> {
                selectedFragment = AccountFragment()
            }
            R.id.nav_settings -> {
                selectedFragment = AboutFragment()
            }
            R.id.nav_logout -> {
                startActivity(Intent(this, LogIn::class.java))
            }
        }
        selectedFragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, it)
                .commit()
        }

        // Close the drawer
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}