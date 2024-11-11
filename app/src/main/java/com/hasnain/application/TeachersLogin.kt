package com.hasnain.application

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import android.content.Intent

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hasnain.application.databinding.ActivityTeachersLoginBinding
import com.hasnain.application.models.TeachersModel

class TeachersLogin : AppCompatActivity() {
    private lateinit var binding: ActivityTeachersLoginBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeachersLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference = FirebaseDatabase.getInstance().reference.child("Teachers")



        binding.btnLogIn.setOnClickListener {
            val username: String = binding.etUserName.text.toString().trim()
            val password: String = binding.etPassword.text.toString().trim()

            if (TextUtils.isEmpty(username)) {
                binding.etUserName.error = "Please enter a username"
                binding.etUserName.requestFocus()
            } else if (TextUtils.isEmpty(password)) {
                binding.etPassword.error = "Please enter a password"
                binding.etPassword.requestFocus()
            } else {
                loginTeacher(username, password)

//                // Query Firebase Database for the entered username
//                databaseReference.child("Teachers").addListenerForSingleValueEvent(
//                    object : ValueEventListener {
//                        override fun onDataChange(snapshot: DataSnapshot) {
//                            if (snapshot.exists()) {
//                                // Username found, check password
//                                val teacher = snapshot.getValue(TeachersModel::class.java)
//                                if (teacher != null && teacher.password == password) {
//                                    // Password matched, login successful
//                                    Toast.makeText(this@TeachersLogin, "Logged in as ${teacher.tname}", Toast.LENGTH_SHORT).show()
//                                    val intent = Intent(this@TeachersLogin, TeachersDashboard::class.java)
//                                    startActivity(intent)
//                                    finish()
//
//                                } else {
//                                    // Password incorrect
//                                    Toast.makeText(this@TeachersLogin, "Incorrect password", Toast.LENGTH_SHORT).show()
//                                }
//                            } else {
//                                // Username not found
//                                Toast.makeText(this@TeachersLogin, "Username not found", Toast.LENGTH_SHORT).show()
//                            }
//                        }
//
//                        override fun onCancelled(error: DatabaseError) {
//                            // Handle database error
//                            Toast.makeText(this@TeachersLogin, "Database error: ${error.message}", Toast.LENGTH_SHORT).show()
//                        }
//                    })
            }
        }
    }
    private fun loginTeacher(username: String, password: String) {
        databaseReference.orderByChild("username").equalTo(username)

            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) = if (snapshot.exists()) {

                    // Assuming there is only one teacher with the given username, otherwise iterate through snapshot.children
                    val teacher = snapshot.children.first().getValue(TeachersModel::class.java)
                    val id = teacher?.tid

                    if (teacher?.password == password) {
                        // Login successful
                            Toast.makeText(this@TeachersLogin, "Login successful", Toast.LENGTH_SHORT).show()
                        // Proceed with your logic (e.g., fetch attendance data, start dashboard activity)
                        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("message", id)
                        editor.apply()
                        val intent = Intent(this@TeachersLogin, TeachersDashboard::class.java)

                        startActivity(intent)
                        finish()
                    } else {
                        // Incorrect password
                        Toast.makeText(this@TeachersLogin, "Incorrect password", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Username not found
                    Toast.makeText(this@TeachersLogin, "Username not found", Toast.LENGTH_SHORT).show()
                }

                override fun onCancelled(error: DatabaseError) {
                    // Database error
                    Toast.makeText(this@TeachersLogin, "Database error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })}

}

