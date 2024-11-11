package com.hasnain.application

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.hasnain.application.adapters.AttendenceDetailsAdaptar
import com.hasnain.application.adapters.TeachersAttendenceAdapter
import com.hasnain.application.databinding.ActivityAttendenceDetailsBinding
import com.hasnain.application.models.TeacherAttendenceModel
import com.hasnain.application.models.TeachersModel

class AttendenceDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAttendenceDetailsBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var attendanceList: MutableList<TeacherAttendenceModel>
    private lateinit var teacherList: MutableList<TeachersModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAttendenceDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val teacherId = sharedPreferences.getString("message", "").toString()

        attendanceList = mutableListOf()
        teacherList = mutableListOf()  // Initialize the teacher list

        binding.recView.layoutManager = LinearLayoutManager(this)
        binding.recView.setHasFixedSize(true)

        fetchTeacherData()
        fetchAttendanceData(teacherId)
    }

    private fun fetchTeacherData() {
        val teachersRef = FirebaseDatabase.getInstance().getReference("Teachers")
        teachersRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                teacherList.clear()
                for (teacherSnapshot in snapshot.children) {
                    val teacher = teacherSnapshot.getValue(TeachersModel::class.java)
                    if (teacher != null) {
                        teacherList.add(teacher)
                    }
                }
                // After fetching teachers, fetch attendance data
                val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
                val teacherId = sharedPreferences.getString("message", "").toString()
                fetchAttendanceData(teacherId)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@AttendenceDetailsActivity,
                    "Failed to load teachers: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun fetchAttendanceData(teacherId: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Attendance").child(teacherId)

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                attendanceList.clear()
                for (dateSnapshot in snapshot.children) {
                    val attendance = dateSnapshot.getValue(TeacherAttendenceModel::class.java)
                    if (attendance != null) {
                        attendanceList.add(attendance)
                    }
                }
                val adapter = AttendenceDetailsAdaptar(attendanceList)
                binding.recView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@AttendenceDetailsActivity,
                    "Database error: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }
}
