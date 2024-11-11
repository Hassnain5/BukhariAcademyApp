package com.hasnain.application

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.hasnain.application.databinding.ActivityAddNewTeacherBinding
import com.hasnain.application.models.TeachersModel

class AddNewTeacherActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityAddNewTeacherBinding

    private lateinit var  dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewTeacherBinding.inflate(layoutInflater)
        setContentView(binding.root)



        dbRef=FirebaseDatabase.getInstance().getReference("Teachers")


        binding.btnAddTeacher.setOnClickListener(){
            saveTeacherData()
        }
    }

    private fun saveTeacherData() {
        val tName = binding.etTeacherName.text.toString()
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        val tSubject = binding.etSubjectName.text.toString()
        val tSalary = binding.etSalary.text.toString()
        val tGender :String


        if (TextUtils.isEmpty(tName)) {
            binding.etTeacherName.error = "Please enter a user name"
            binding.etTeacherName.requestFocus()

        } else if (TextUtils.isEmpty(username)) {
            binding.etUsername?.error = "Please enter a password"
            binding.etUsername.requestFocus()
        }else if (TextUtils.isEmpty(password)) {
            binding.etPassword?.error = "Please enter a password"
            binding.etPassword.requestFocus()
        }else if (TextUtils.isEmpty(tSubject)) {
            binding.etSubjectName?.error = "Please enter a password"
            binding.etSubjectName.requestFocus()
        } else if (TextUtils.isEmpty(tSalary)) {
            binding.etSalary?.error = "Please enter a password"
            binding.etSalary.requestFocus()
        }  else {
            val teacherid = dbRef.push().key


            if (binding.radioMale.isChecked) {

                tGender = "male"
            }
            else
                tGender="female"

            val teacher = TeachersModel(teacherid ,tName,username,password, tSubject ,tSalary ,tGender )

            if (teacherid != null) {
                dbRef.child(teacherid).setValue(teacher).addOnCompleteListener( ){
                    Toast.makeText(this, "New Teacher added succsesfully", Toast.LENGTH_SHORT).show()

                    binding.etTeacherName.text.clear()
                    binding.etUsername.text.clear()
                    binding.etPassword.text.clear()
                    binding.etSubjectName.text.clear()
                    binding.etSalary.text.clear()
                    binding.radioGroupGender.clearCheck()


                }.addOnFailureListener() { err ->
                    Toast.makeText(this, "Error : ${err.message}", Toast.LENGTH_SHORT).show()
                }
            }


        }
    }
}