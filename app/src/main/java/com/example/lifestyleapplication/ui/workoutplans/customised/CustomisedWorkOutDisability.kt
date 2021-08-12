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
import com.example.lifestyleapplication.databinding.FragmentCustomisedWorkOutDisabilityBinding

class CustomisedWorkOutDisability : Fragment() {
    private lateinit var binding: FragmentCustomisedWorkOutDisabilityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCustomisedWorkOutDisabilityBinding.inflate(inflater, container, false)
        binding.floatingBodyType.setOnClickListener {
            setData()
        }
        setUsername()
        return binding.root
    }

    private fun setData() {
        val id = binding.radioBodyTypes.checkedRadioButtonId
        when(id){
            R.id.radioYes -> {

            }
            R.id.radioNo -> {
                val sharedPreferencesGend: SharedPreferences = activity?.getSharedPreferences("CUSTOMIZEDWORKOUT", Context.MODE_PRIVATE)!!
                val edt: SharedPreferences.Editor = sharedPreferencesGend.edit()
                edt.putString("DISABILITY", binding.radioNo.text.toString())
                edt.apply()
                findNavController().navigate(R.id.action_customisedWorkOutDisability_to_customisedWorkOutDays)
            }
            else -> {

            }
        }
    }

    private fun setUsername() {
        val sharedPreferences = activity?.getSharedPreferences("USER", Context.MODE_PRIVATE)!!
        val username = sharedPreferences.getString("USERNAME", "")
        if (username != null){
            binding.introBodyType.text = username.toString() + ", are you facing any disability or Health Condition?"
        }

    }

}