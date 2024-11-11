package com.hasnain.application

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.text.Paragraph
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.hasnain.application.databinding.ActivityAddAnouncmentBinding
import com.hasnain.application.databinding.ActivityPayDetailsBinding

class AddAnouncmentActivity : AppCompatActivity() {
private lateinit var binding: ActivityAddAnouncmentBinding

    private val databaseReference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddAnouncmentBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.saveButton.setOnClickListener {
            val paragraph = binding.paragraphEditText.text.toString()
            saveParagraphToFirebase(paragraph)
        }}

        private fun saveParagraphToFirebase(paragraph: String) {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("paragraph")
            myRef.setValue(paragraph)
        }
    }
