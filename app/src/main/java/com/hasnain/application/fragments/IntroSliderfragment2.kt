package com.hasnain.application.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hasnain.application.R
import com.hasnain.application.SignUp
import com.hasnain.application.databinding.FragmentIntroSliderfragment2Binding


class IntroSliderfragment2 : Fragment() {
    private lateinit var binding: FragmentIntroSliderfragment2Binding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentIntroSliderfragment2Binding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set onClickListener for btnTopUp
        binding.btnGetStarted.setOnClickListener {
            val intent = Intent(requireContext(), SignUp::class.java)
            startActivity(intent)
        }
    }

    }
