package com.hasnain.application


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.hasnain.application.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {
    private lateinit var binding:ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth=FirebaseAuth.getInstance()

        binding.btnSignUp.setOnClickListener {
//            Toast.makeText(this, "SignUp button is clicked", Toast.LENGTH_SHORT).show()
            val fullname = binding.etFullName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isNotEmpty()&& password.isNotEmpty()) {
                firebaseAuth.createUserWithEmailAndPassword(email, password)

                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Sign up Successfull", Toast.LENGTH_LONG).show()

                            val intent = Intent(this, TeachersDashboard::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Sign up Failed", Toast.LENGTH_LONG).show()

                        }

                    }
            }
            else{
            Toast.makeText(this, "Enter Email and Password", Toast.LENGTH_LONG).show()

        }

//            if(TextUtils.isEmpty(fullname)){
////                Toast.makeText(this, "Please Enter Fullname", Toast.LENGTH_LONG).show()
//                binding.etFullName?.error = "Please Enter Fullname"
//                binding.etEmail.requestFocus()
//            }
//            else if(TextUtils.isEmpty(email)){
//                binding.etEmail?.error = "Please Enter a valid password"
//                binding.etEmail.requestFocus()
//            }
//            else if(TextUtils.isEmpty(password)) {
//                binding.etPassword?.error = "Please Enter a valid password"
//                binding.etPassword.requestFocus()
//
//            }



        }
        binding.tvGoToLogin.setOnClickListener {
            val i = Intent(this, LogIn::class.java)
            startActivity(i)
        }



    }
}
