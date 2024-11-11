package com.hasnain.application

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
//import com.hasnain.carappassignment.databinding.ActivityPaymentMethodBinding
//import com.hasnain.carappassignment.databinding.ActivityTopUpWalletBinding
//import com.hasnain.carappassignment.databinding.FragmentWalletBinding

class PaymentMethod : AppCompatActivity() {
//    private lateinit var binding: ActivityPaymentMethodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment_method)

//        binding= ActivityPaymentMethodBinding.inflate(layoutInflater)
//        setContentView(binding.root)

//        binding.btnContinueToPin.setOnClickListener(){
//            val i= Intent(this,ConfirmPin::class.java)
//            startActivity(i)
//        }

    }
}