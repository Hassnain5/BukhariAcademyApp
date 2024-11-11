package com.hasnain.application.adapters

import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import com.hasnain.application.R
import com.hasnain.application.models.TeachersModel

class TeachersAdapter(private val tList: MutableList<TeachersModel>) : RecyclerView.Adapter<TeachersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.teacher_name_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTeacher = tList[position]
        holder.tvTName.text = currentTeacher.tname
        holder.tvTSubject.text = currentTeacher.tsubject
        holder.tvTSalary.text = currentTeacher.tsalary

        holder.ivDelete.setOnClickListener {
            val teacherId = currentTeacher.tid
            if (teacherId != null) {
                val dbRefTeacher = FirebaseDatabase.getInstance().getReference("Teachers").child(teacherId)
                val dbRefAttendance = FirebaseDatabase.getInstance().getReference("Attendance").child(teacherId)

                dbRefTeacher.removeValue().addOnSuccessListener {
                    dbRefAttendance.removeValue().addOnSuccessListener {
                        if (position >= 0 && position < tList.size) {
                            tList.removeAt(position)
                            notifyItemRemoved(position)
                            notifyItemRangeChanged(position, tList.size)
                            Toast.makeText(holder.itemView.context, "Teacher and attendance records removed", Toast.LENGTH_SHORT).show()
                        }
                    }.addOnFailureListener {
                        Toast.makeText(holder.itemView.context, "Failed to remove attendance records", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener {
                    Toast.makeText(holder.itemView.context, "Failed to remove teacher", Toast.LENGTH_SHORT).show()
                }
            }
        }

        holder.ivUpdate.setOnClickListener {
            showUpdateDialog(holder.itemView, currentTeacher)
        }
    }

    override fun getItemCount(): Int {
        return tList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTName: TextView = itemView.findViewById(R.id.tv_t_name)
        val tvUName: TextView = itemView.findViewById(R.id.tv_t_name)
        val tvPass: TextView = itemView.findViewById(R.id.tv_t_name)
        val tvTSubject: TextView = itemView.findViewById(R.id.tv_t_subject)
        val tvTSalary: TextView = itemView.findViewById(R.id.tv_t_salary)
        val ivDelete: LinearLayout = itemView.findViewById(R.id.del)
        val ivUpdate: LinearLayout = itemView.findViewById(R.id.update)
    }

    private fun showUpdateDialog(view: View, teacher: TeachersModel) {
        val builder = AlertDialog.Builder(view.context)
        val inflater = LayoutInflater.from(view.context)
        val dialogView = inflater.inflate(R.layout.update_teacher_dialogue, null)
        builder.setView(dialogView)

        val editName = dialogView.findViewById<EditText>(R.id.edit_teacher_name)
        val editSubject = dialogView.findViewById<EditText>(R.id.edit_subject)
        val editSalary = dialogView.findViewById<EditText>(R.id.edit_salary)
        val radioGroupGender = dialogView.findViewById<RadioGroup>(R.id.radio_group_gender)
        val radioMale = dialogView.findViewById<RadioButton>(R.id.radio_male)
        val radioFemale = dialogView.findViewById<RadioButton>(R.id.radio_female)

        editName.setText(teacher.tname)
        val uname =teacher.username
        val password =teacher.password
        editSubject.setText(teacher.tsubject)
        editSalary.setText(teacher.tsalary)

        if (teacher.tgender == "Male") {
            radioMale.isChecked = true
        } else {
            radioFemale.isChecked = true
        }

        builder.setTitle("Update Teacher Details")
        builder.setPositiveButton("Update") { _, _ ->
            val name = editName.text.toString().trim()
            val subject = editSubject.text.toString().trim()
            val salary = editSalary.text.toString().trim()
            val selectedGender = if (radioMale.isChecked) "Male" else "Female"

            if (name.isEmpty() || subject.isEmpty() || salary.isEmpty()) {
                Toast.makeText(view.context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }

            val updatedTeacher = TeachersModel(teacher.tid, name,uname,password, subject, salary, selectedGender)
            val dbRef = FirebaseDatabase.getInstance().getReference("Teachers").child(teacher.tid.toString())
            dbRef.setValue(updatedTeacher).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(view.context, "Teacher updated", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(view.context, "Update failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }

        val alertDialog = builder.create()
        alertDialog.show()
    }
}
