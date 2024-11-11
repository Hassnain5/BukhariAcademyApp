package com.hasnain.application.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AttendanceDetailsAdapter(
    private val attendanceRecords: MutableMap<String, String>
) : RecyclerView.Adapter<AttendanceDetailsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_2, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date = attendanceRecords.keys.toList()[position]
        val status = attendanceRecords[date]
        holder.dateTextView.text = date
        holder.statusTextView.text = status
    }

    override fun getItemCount(): Int {
        return attendanceRecords.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.findViewById(android.R.id.text1)
        val statusTextView: TextView = itemView.findViewById(android.R.id.text2)
    }
}
