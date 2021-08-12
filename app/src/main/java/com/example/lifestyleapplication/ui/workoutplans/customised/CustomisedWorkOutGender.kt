package com.example.lifestyleapplication.ui.workoutplans.customised

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentCustomisedWorkOutGenderBinding
import com.example.lifestyleapplication.ui.interfaces.generalinterface

class CustomisedWorkOutGender : Fragment() {
    private lateinit var binding: FragmentCustomisedWorkOutGenderBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var general: generalinterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCustomisedWorkOutGenderBinding.inflate(inflater, container, false)
        binding.floatingGender.setOnClickListener {
            setData()
        }
        setUsername()
        return binding.root
    }

    private fun setData() {
        val id = binding.radioGroup.checkedRadioButtonId
        when(id){
            R.id.radioMale -> {
                val sharedPreferencesGend: SharedPreferences = activity?.getSharedPreferences("CUSTOMIZEDWORKOUT", Context.MODE_PRIVATE)!!
                val edt: SharedPreferences.Editor = sharedPreferencesGend.edit()
                edt.putString("GENDER", binding.radioMale.text.toString())
                edt.apply()
                findNavController().navigate(R.id.action_customisedWorkOutGender_to_customisedWorkOutWeight)
            }
            R.id.radioFemale -> {
                val sharedPreferencesGend: SharedPreferences = activity?.getSharedPreferences("CUSTOMIZEDWORKOUT", Context.MODE_PRIVATE)!!
                val edt: SharedPreferences.Editor = sharedPreferencesGend.edit()
                edt.putString("GENDER", binding.radioFemale.text.toString())
                edt.apply()
                findNavController().navigate(R.id.action_customisedWorkOutGender_to_customisedWorkOutWeight)
            }
            else -> {

            }
        }

    }

    private fun setUsername() {
        sharedPreferences = activity?.getSharedPreferences("USER", Context.MODE_PRIVATE)!!
        val username = sharedPreferences.getString("USERNAME", "")
        if (username != null){
            binding.introGender.text = "What is your gender " + username.toString() + "?"
        }

    }

}