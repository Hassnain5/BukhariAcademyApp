package com.hasnain.application

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.hasnain.application.adapters.TeachersAttendenceAdapter
import com.hasnain.application.databinding.ActivityTeachersAttendenceBinding
import com.hasnain.application.models.TeacherAttendenceModel
import com.hasnain.application.models.TeachersModel

class TeachersAttendence : AppCompatActivity() {

    private lateinit var  binding: ActivityTeachersAttendenceBinding
    private lateinit var totalDaysTextView: TextView
    private lateinit var totalAbsentTextView: TextView
    private lateinit var totalPresentTextView: TextView
    private lateinit var teacherId: String
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityTeachersAttendenceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnViewAll.setOnClickListener(){
            startActivity(Intent(this,AttendenceDetailsActivity::class.java))
        }

        totalDaysTextView = binding.textTotalDaysNumber
        totalAbsentTextView = binding.textTotalAbsentNumber
        totalPresentTextView = binding.textTotalPresentNumber
//        teacherId = intent.getStringExtra("message") as String
        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        teacherId = sharedPreferences.getString("message", "").toString()
        fetchAttendanceData(teacherId)
//
//
//
//        databaseReference = FirebaseDatabase.getInstance().getReference("Teachers")
//
//        recyclerView = findViewById(R.id.rec_view)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        teacherList = mutableListOf()
//        teacherAdapter = TeachersAttendenceAdapter(teacherList, this)
//        recyclerView.adapter = teacherAdapter
//
//        loadTeachers()
//    }
//
//    private fun loadTeachers() {
//        databaseReference.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                teacherList.clear()
//                for (teacherSnapshot in snapshot.children) {
//                    val teacher = teacherSnapshot.getValue(TeachersModel::class.java)
//                    if (teacher != null) {
//                        loadAttendance(teacher)
//                    }
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Handle database error
//
//            }
//        })
//    }
//
//    private fun loadAttendance(teacher: TeachersModel) {
//        val attendanceRef = FirebaseDatabase.getInstance().getReference("Attendance").child(teacher.tid!!)
//        attendanceRef.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val attendanceMap = mutableMapOf<String, String>()
//                for (attendanceSnapshot in snapshot.children) {
//                    val date = attendanceSnapshot.key ?: continue
//                    val isPresent = attendanceSnapshot.getValue(String::class.java) ?: continue
//                    attendanceMap[date] = isPresent
//                }
//                teacher.attendanceRecords = attendanceMap
//                teacherList.add(teacher)
//                teacherAdapter.notifyDataSetChanged()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Handle database error
//
//            }
//        })
//    }
        }

//    private fun fetchAttendanceData(teacherId: String) {
//        val attendanceReference = databaseReference
//            .child("Attendance")
//            .child(teacherId)
//
//        attendanceReference.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                var totalDays = 0
//                var totalAbsent = 0
//                var totalPresent = 0
//
//                // Iterate through each date node under the teacher's attendance node
//                for (dateSnapshot in snapshot.children) {
//                    // Assuming each child node under teacherId is a date node
//                    val date = dateSnapshot.key ?: continue
//                    val present = dateSnapshot.child("present").getValue(String::class.java)
//
//                    // Increment total days
//                    totalDays++
//
//                    // Count present and absent based on 'present' status
//                    if (present == "Present") {
//                        totalPresent++
//                    } else {
//                        totalAbsent++
//                    }
//                }
//
//                // Update TextViews with fetched data on the UI thread
//                runOnUiThread {
//                    totalDaysTextView.text = totalDays.toString()
//                    totalAbsentTextView.text = totalAbsent.toString()
//                    totalPresentTextView.text = totalPresent.toString()
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Handle database error
//                runOnUiThread {
//                    Toast.makeText(this@TeachersAttendence, "Database error: ${error.message}", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })
//    }


    private fun fetchAttendanceData(teacherId: String) {
        val attendanceReference = FirebaseDatabase.getInstance().reference
            .child("Attendance")
            .child(teacherId)

        attendanceReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var totalDays = 0
                var totalAbsent = 0
                var totalPresent = 0

                for (dateSnapshot in snapshot.children) {
                    val present = dateSnapshot.child("isPresent").getValue(String::class.java)

                    totalDays++
                    if (present == "Present") {
                        totalPresent++
                    } else {
                        totalAbsent++
                    }
                }

                runOnUiThread {
                    totalDaysTextView.text = totalDays.toString()
                    totalAbsentTextView.text = totalAbsent.toString()
                    totalPresentTextView.text = totalPresent.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                runOnUiThread {
                    Toast.makeText(this@TeachersAttendence, "Database error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }



}


