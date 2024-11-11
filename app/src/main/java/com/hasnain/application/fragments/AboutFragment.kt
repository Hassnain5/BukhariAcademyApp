package com.hasnain.application.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hasnain.application.R
import com.hasnain.application.databinding.FragmentAboutBinding
import com.hasnain.application.databinding.FragmentAccountBinding
import java.util.Locale


class AboutFragment : Fragment() {
    private var fBinding: FragmentAboutBinding? =null
    private val binding get()=fBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):   View? {
        // Inflate the layout for this fragment
        fBinding = FragmentAboutBinding.inflate(inflater,container,false)
       return binding?.root


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.tvSelectLang?.setOnClickListener {
            showLanguageSelectionDialog()
        }
    }

    private fun showLanguageSelectionDialog() {
        val languages = arrayOf("English", "French", "Spanish") // Replace with your supported languages
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Select Language")
        alertDialogBuilder.setItems(languages) { _, which ->
            val selectedLanguage = languages[which]
            setLocale(selectedLanguage)
            restartActivity(requireActivity())
        }
        alertDialogBuilder.create().show()
    }

    private fun setLocale(language: String) {
        val locale = when (language) {
            "English" -> Locale("en")
            "French" -> Locale("fr")
            "Spanish" -> Locale("es")
            else -> Locale("en")
        }
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)

        // Save selected language preference if needed
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("language", language)
            apply()
        }
    }

    private fun restartActivity(activity: androidx.fragment.app.FragmentActivity) {
        val intent = Intent(activity, activity.javaClass)
        activity.finish()
        activity.startActivity(intent)
    }
}
