package com.hasnain.application

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.hasnain.application.adapters.TeachersAttendenceAdapter
import com.hasnain.application.databinding.ActivityAttendenceBinding
import com.hasnain.application.models.TeacherAttendenceModel
import com.hasnain.application.models.TeachersModel
import java.util.Calendar

class AttendenceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAttendenceBinding
    private lateinit var teacherList: ArrayList<TeachersModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAttendenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recView.layoutManager = LinearLayoutManager(this)
        binding.recView.setHasFixedSize(true)

        teacherList = arrayListOf()
        getTeachersName()

        binding.btnSubmit.setOnClickListener {
            for (i in 0 until teacherList.size) {
                val viewHolder = binding.recView.findViewHolderForAdapterPosition(i) as? TeachersAttendenceAdapter.ViewHolder
                viewHolder?.let {
                    val teacherId = teacherList[i].tid
                    val isPresent = viewHolder.radioPresent.isChecked
                    val currentDate = Calendar.getInstance()
                    val year = currentDate.get(Calendar.YEAR)
                    val month = currentDate.get(Calendar.MONTH) + 1
                    val day = currentDate.get(Calendar.DAY_OF_MONTH)
                    val formattedDate = "$year-$month-$day"
                    if (teacherId != null) {
                        addAttendance(teacherId, formattedDate, isPresent)
                    }
                }
            }
        }
    }

    private fun getTeachersName() {
        binding.loadingAnim.visibility = View.VISIBLE
        binding.recView.visibility = View.GONE
        dbRef = FirebaseDatabase.getInstance().getReference("Teachers")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                teacherList.clear()
                if (snapshot.exists()) {
                    binding.loadingAnim.visibility = View.GONE
                    binding.recView.visibility = View.VISIBLE
                    for (teachersnap in snapshot.children) {
                        val teacherData = teachersnap.getValue(TeachersModel::class.java)
                        if (teacherData != null) {
                            teacherList.add(teacherData)
                        }
                    }

                    val teacherAdapter = TeachersAttendenceAdapter(teacherList, teacherList)
                    binding.recView.adapter = teacherAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error
            }
        })
    }

    private fun addAttendance(teacherId: String, date: String, isPresent: Boolean) {
        val attendanceStatus = if (isPresent) "Present" else "Absent"
        val attendanceRef = FirebaseDatabase.getInstance().getReference("Attendance").child(teacherId).child(date)
        val attendance = TeacherAttendenceModel(date, attendanceStatus)

        attendanceRef.setValue(attendance).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Attendance saved successfully for $teacherId", Toast.LENGTH_SHORT).show()
            } else {
                task.exception?.let { exception ->
                    Log.e("FirebaseError", "Error saving attendance", exception)
                    Toast.makeText(this, "Failed to save attendance for $teacherId: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
