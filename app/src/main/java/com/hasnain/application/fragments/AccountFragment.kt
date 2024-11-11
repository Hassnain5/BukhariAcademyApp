package com.hasnain.application.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hasnain.application.R
import com.hasnain.application.databinding.FragmentAboutBinding
import com.hasnain.application.databinding.FragmentAccountBinding


class AccountFragment : Fragment() {
    private var fBinding: FragmentAccountBinding? =null
    private val binding get()=fBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fBinding = FragmentAccountBinding.inflate(inflater,container,false)
        return binding?.root
    }
}