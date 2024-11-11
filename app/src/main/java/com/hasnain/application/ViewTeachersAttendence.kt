package com.hasnain.application

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.hasnain.application.adapters.ShowAttendenceAdapter
import com.hasnain.application.models.TeacherAttendenceModel
import com.hasnain.application.models.TeachersModel
import com.hasnain.application.databinding.ActivityViewTeachersAttendenceBinding
import android.app.AlertDialog
import android.view.LayoutInflater
import android.widget.TextView
import com.hasnain.application.adapters.AttendanceDetailsAdapter

class ViewTeachersAttendence : AppCompatActivity() {
    private lateinit var binding: ActivityViewTeachersAttendenceBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var attendanceAdapter: ShowAttendenceAdapter
    private lateinit var teacherList: MutableList<TeachersModel>
    private lateinit var attendanceRef: DatabaseReference
    private lateinit var teacherRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewTeachersAttendenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recView
        recyclerView.layoutManager = LinearLayoutManager(this)
        teacherList = mutableListOf()
        attendanceAdapter = ShowAttendenceAdapter(teacherList, this)
        recyclerView.adapter = attendanceAdapter

        val database = FirebaseDatabase.getInstance()
        attendanceRef = database.getReference("Attendance")
        teacherRef = database.getReference("Teachers")

        fetchAttendanceData()
        attendanceAdapter.onItemClick = { teacher ->
            showAttendancePopup(teacher)
        }
    }

    private fun fetchAttendanceData() {
        teacherRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(teacherSnapshot: DataSnapshot) {
                val fetchedTeachersList = mutableListOf<TeachersModel>()

                teacherSnapshot.children.forEach { teacherDataSnapshot ->
                    val userId = teacherDataSnapshot.key ?: return
                    val tname = teacherDataSnapshot.child("tname").getValue(String::class.java) ?: "Unknown"
                    val teacher = TeachersModel(tid = userId, tname = tname)

                    attendanceRef.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(attendanceSnapshot: DataSnapshot) {
                            val attendanceRecords = mutableMapOf<String, String>()

                            attendanceSnapshot.children.forEach { dateSnapshot ->
                                val date = dateSnapshot.key ?: return
                                val isPresent = dateSnapshot.child("isPresent").getValue(String::class.java) ?: "Absent"
                                attendanceRecords[date] = isPresent
                            }

                            teacher.attendanceRecords = attendanceRecords
                            fetchedTeachersList.add(teacher)
                            teacherList.clear()
                            teacherList.addAll(fetchedTeachersList)
                            attendanceAdapter.notifyDataSetChanged()
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(this@ViewTeachersAttendence, "Failed to fetch attendance data.", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ViewTeachersAttendence, "Failed to fetch teacher data.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showAttendancePopup(teacher: TeachersModel) {
        val inflater = LayoutInflater.from(this)
        val popupView = inflater.inflate(R.layout.popup_attendance_details, null)

        val tvTeacherNamePopup: TextView = popupView.findViewById(R.id.tv_teacher_name_popup)
        val recViewAttendanceDetails: RecyclerView = popupView.findViewById(R.id.recView_attendance_details)

        tvTeacherNamePopup.text = teacher.tname

        val attendanceDetailsAdapter = AttendanceDetailsAdapter(teacher.attendanceRecords)
        recViewAttendanceDetails.layoutManager = LinearLayoutManager(this)
        recViewAttendanceDetails.adapter = attendanceDetailsAdapter

        val dialog = AlertDialog.Builder(this)
            .setView(popupView)
            .setPositiveButton("Close", null)
            .create()

        dialog.show()
    }
}
