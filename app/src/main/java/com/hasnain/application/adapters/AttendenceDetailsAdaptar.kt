package com.hasnain.application.adapters

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hasnain.application.R
import com.hasnain.application.models.TeacherAttendenceModel
import java.text.SimpleDateFormat
import java.util.Locale

class AttendenceDetailsAdaptar(
    private val attendanceList: List<TeacherAttendenceModel>
) : RecyclerView.Adapter<AttendenceDetailsAdaptar.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.attendence_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentAttendance = attendanceList[position]
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = try {
            dateFormat.parse(currentAttendance.date)
        } catch (e: Exception) {
            null
        }

        if (date != null) {
            val dayFormat = SimpleDateFormat("d", Locale.getDefault())
            val monthFormat = SimpleDateFormat("MMMM", Locale.getDefault())

            holder.tvDay.text = dayFormat.format(date)
            holder.tvMonth.text = monthFormat.format(date)
        } else {
            holder.tvDay.text = ""
            holder.tvMonth.text = ""
        }


        holder.tvAttendanceStatus.text = currentAttendance.isPresent
        val drawable = GradientDrawable()
        drawable.shape = GradientDrawable.RECTANGLE
        drawable.cornerRadius = 100f
        if (currentAttendance.isPresent.equals("present", ignoreCase = true)) {
            drawable.setColor(android.graphics.Color.GREEN)
        } else {
            drawable.setColor(android.graphics.Color.RED)
        }

        // Set the drawable as background
        holder.tvAttendencecolor.background = drawable
    }

    override fun getItemCount(): Int {
        return attendanceList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDay: TextView = itemView.findViewById(R.id.tvDay)
        val tvMonth: TextView = itemView.findViewById(R.id.tvMonth)
        val tvAttendanceStatus: TextView = itemView.findViewById(R.id.tvAttendanceStatus)
        val tvAttendencecolor: View = itemView.findViewById(R.id.attendence_color)
    }
}
