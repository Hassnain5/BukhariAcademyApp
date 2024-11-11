package com.hasnain.application

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.hasnain.application.databinding.ActivityTeachersDashboardBinding


class TeachersDashboard : AppCompatActivity() {
    private lateinit var  binding : ActivityTeachersDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeachersDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val message = intent.getStringExtra("attendanceid")

        binding.cardattendence.setOnClickListener(){
            intent.putExtra("attendanceid", message)
            startActivity(Intent(this,TeachersAttendence::class.java))
        }
    }
}