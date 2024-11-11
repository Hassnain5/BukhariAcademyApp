package com.hasnain.application

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hasnain.application.databinding.ActivityAdminDashboardBinding
import com.hasnain.application.databinding.ActivityLoginBinding

class AdminDashboard : AppCompatActivity() {
    private lateinit var  binding: ActivityAdminDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.cardViewTeachers.setOnClickListener {
            val i = Intent(this, ViewTeachersActivity::class.java)
            startActivity(i)
        }
        binding.cardPaydetails.setOnClickListener {
            val i = Intent(this, PayDetailsActivity::class.java)
            startActivity(i)
        }
        binding.cardattendence.setOnClickListener {
            val i = Intent(this, AttendenceActivity::class.java)
            startActivity(i)
        }

        binding.cardSettings.setOnClickListener {
            val i = Intent(this, AddAnouncmentActivity::class.java)
            startActivity(i)
        }
        binding.cardNotifications.setOnClickListener {
            val i = Intent(this, ViewTeachersAttendence::class.java)
            startActivity(i)
        }
    }
}