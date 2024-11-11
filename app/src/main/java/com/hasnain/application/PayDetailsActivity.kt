package com.hasnain.application

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hasnain.application.adapters.TeachersAdapter
import com.hasnain.application.adapters.TeachersPayAdapter
import com.hasnain.application.databinding.ActivityPayDetailsBinding
import com.hasnain.application.databinding.ActivityViewTeachersBinding
import com.hasnain.application.models.TeachersModel

class PayDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPayDetailsBinding


        private lateinit var teacherList: ArrayList<TeachersModel>
        private lateinit var dbRef: DatabaseReference

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityPayDetailsBinding.inflate(layoutInflater)
            setContentView(binding.root)
//
//            binding.btnAddNewTeacher.setOnClickListener {
//                val i = Intent(this, AddNewTeacherActivity::class.java)
//                startActivity(i)
//            }

            binding.recView.layoutManager = LinearLayoutManager(this)
            binding.recView.setHasFixedSize(true)

            teacherList = arrayListOf()
            getTeachersData()
        }

        private fun getTeachersData() {
            binding.loadingAnim.setVisibility(View.VISIBLE)
            binding.recView.setVisibility(View.GONE)
            dbRef = FirebaseDatabase.getInstance().getReference("Teachers")
            dbRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    teacherList.clear()
                    if (snapshot.exists()) {
                        binding.loadingAnim.setVisibility(View.GONE)
                        binding.recView.setVisibility(View.VISIBLE)
                        for (teachersnap in snapshot.children) {
                            val teacherData = teachersnap.getValue(TeachersModel::class.java)
                            if (teacherData != null) {
                                teacherList.add(teacherData)
                            }
                        }
                        val mAdapter = TeachersPayAdapter(teacherList)
                        binding.recView.adapter = mAdapter
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle database error
                }
            })
        }
    }
