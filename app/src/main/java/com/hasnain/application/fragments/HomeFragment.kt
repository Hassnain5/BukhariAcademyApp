package com.hasnain.application.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hasnain.application.AdminDashboard
import com.hasnain.application.LogIn
import com.hasnain.application.TeachersDashboard
import com.hasnain.application.TeachersLogin
import com.hasnain.application.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var Binding: FragmentHomeBinding? =null
    private val binding get()=Binding
    private lateinit var paragraphTextView: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding?.root
//        paragraphTextView=Binding.paragraphTextView

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set onClickListener for btnTopUp
        binding?.teachersCard?.setOnClickListener {
            val intent = Intent(requireContext(), TeachersLogin::class.java)
            startActivity(intent)
        }
        binding?.adminCard?.setOnClickListener {
            val intent = Intent(requireContext(), LogIn::class.java)
            startActivity(intent)
        }
        fetchParagraphFromFirebase()
    }

    private fun fetchParagraphFromFirebase() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("paragraph")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val paragraph = snapshot.getValue(String::class.java)
                paragraphTextView= binding!!.paragraphTextView
                paragraphTextView.text = paragraph
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle possible errors
            }
        })
    }
}