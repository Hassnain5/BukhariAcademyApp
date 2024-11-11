package com.hasnain.application

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.hasnain.application.databinding.ActivityLoginBinding

class LogIn : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogIn.setOnClickListener {
            Toast.makeText(this, "LogIn button is clicked", Toast.LENGTH_SHORT).show()
            val username: String = binding.etUserName.text.toString().trim()
            val password: String = binding.etPassword.text.toString()

            if (TextUtils.isEmpty(username)) {
                binding.etUserName.error = "Please enter a user name"
                binding.etUserName.requestFocus()
            } else if (TextUtils.isEmpty(password)) {
                binding.etPassword.error = "Please enter a password"
                binding.etPassword.requestFocus()
            } else {
                loginUser(username, password)
            }
        }

//        binding.tvGoToSignup.setOnClickListener {
//            val i = Intent(this, SignUp::class.java)
//            startActivity(i)
//        }
    }

    private fun loginUser(username: String, password: String) {
        val database = FirebaseDatabase.getInstance().reference.child("Admin")
        database.child(username).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = task.result
                if (user.exists()) {
                    val dbPassword = user.child("password").value.toString().trim()
                    if (dbPassword == password) {
                        // Password match, login successful
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, AdminDashboard::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // Password mismatch
                        Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Username not found
                    Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Database error
                Toast.makeText(this, "Login failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
