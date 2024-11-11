package com.hasnain.application.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hasnain.application.R
import com.hasnain.application.models.TeachersModel

class TeachersPayAdapter(private val tList: ArrayList<TeachersModel>) : RecyclerView.Adapter<TeachersPayAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeachersPayAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.teachers_salary_card, parent, false)
        return TeachersPayAdapter.ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return tList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTeacher = tList[position]
        holder.tvTName.text = currentTeacher.tname
        holder.tvTSalary.text = currentTeacher.tsalary
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTName: TextView = itemView.findViewById(R.id.tv_teacher_name)
        val tvTSalary: TextView = itemView.findViewById(R.id.tv_teacher_salary)
//        val ivDelete: LinearLayout = itemView.findViewById(R.id.del)
//        val ivUpdate: LinearLayout = itemView.findViewById(R.id.update)
    }

}