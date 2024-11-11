package com.hasnain.application.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hasnain.application.databinding.FragmentGetStartedBinding
import com.hasnain.application.LogIn
//import com.hasnain.carappassignment.MainActivity
//import com.hasnain.carappassignment.R

//import com.hasnain.carappassignment.databinding.FragmentGetStartedBinding

class GetStartedFragment : Fragment() {
    private lateinit var binding: FragmentGetStartedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGetStartedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set onClickListener for btnTopUp
        binding.btnGetStarted.setOnClickListener {
            val intent = Intent(requireContext(), LogIn::class.java)
            startActivity(intent)
        }
    }
}
