package com.hasnain.application.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hasnain.application.R
import com.hasnain.application.models.TeacherAttendenceModel
import com.hasnain.application.models.TeachersModel
class ShowAttendenceAdapter(
    private val tList: MutableList<TeachersModel>,
    private val context: Context
) : RecyclerView.Adapter<ShowAttendenceAdapter.ViewHolder>() {

    var onItemClick: ((TeachersModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.show_attaendence_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTeacher = tList[position]
        holder.tvTName.text = currentTeacher.tname

        val presentDays = currentTeacher.attendanceRecords.values.count { it.equals("Present", ignoreCase = true) }
        val absentDays = currentTeacher.attendanceRecords.values.count { it.equals("Absent", ignoreCase = true) }
        val totalDays = currentTeacher.attendanceRecords.size

        holder.tvTPresentDays.text = presentDays.toString()
        holder.tvTAbsentDays.text = absentDays.toString()
        holder.tvTotalDays.text = totalDays.toString()

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(currentTeacher)
        }
    }


    override fun getItemCount(): Int {
        return tList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTName: TextView = itemView.findViewById(R.id.tv_teacher_name)
        val tvTPresentDays: TextView = itemView.findViewById(R.id.tv_teacher_present_days)
        val tvTAbsentDays: TextView = itemView.findViewById(R.id.tv_teacher_absent_days)
        val tvTotalDays: TextView = itemView.findViewById(R.id.tv_total_days)
    }
}
