package com.hasnain.application.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hasnain.application.R
import com.hasnain.application.models.TeachersModel

class TeachersAttendenceAdapter(
    private val tList: ArrayList<TeachersModel>,
    teacherList: MutableList<TeachersModel>
) : RecyclerView.Adapter<TeachersAttendenceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.teachers_attendence_cheackbox, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder , position: Int) {
        val currentTeacher = tList[position]
        holder.tvTName.text = currentTeacher.tname.toString()
        holder.radioPresent.isChecked = true // default selection


    }

    override fun getItemCount(): Int {
        return tList.size
    }

    class ViewHolder(itemView: View ) : RecyclerView.ViewHolder(itemView) {
        val tvTName: TextView = itemView.findViewById(R.id.tvTeacherNamee)
        val radioPresent: RadioButton = itemView.findViewById(R.id.radio_present)
        val radioAbsent: RadioButton = itemView.findViewById(R.id.radio_absent)

    }
}
//
//if (currentAttendance.isPresent=="present") {
//    attendanceHolder.tvAttendencecolor.setBackgroundColor(android.graphics.Color.GREEN)
//}
//else
//attendanceHolder.tvAttendencecolor.setBackgroundColor(android.graphics.Color.RED)