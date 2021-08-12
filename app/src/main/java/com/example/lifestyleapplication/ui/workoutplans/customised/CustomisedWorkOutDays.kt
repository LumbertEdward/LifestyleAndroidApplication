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
import com.example.lifestyleapplication.databinding.FragmentCustomisedWorkOutDaysBinding

class CustomisedWorkOutDays : Fragment() {
    private lateinit var binding: FragmentCustomisedWorkOutDaysBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCustomisedWorkOutDaysBinding.inflate(inflater, container, false)
        binding.floatingAge.setOnClickListener {
            setData()
        }
        return binding.root
    }

    private fun setData() {
        val sharedPreferencesGend: SharedPreferences = activity?.getSharedPreferences("CUSTOMIZEDWORKOUT", Context.MODE_PRIVATE)!!
        val edt: SharedPreferences.Editor = sharedPreferencesGend.edit()
        edt.putString("DAYS", binding.editDays.text.toString())
        edt.apply()
        findNavController().navigate(R.id.action_customisedWorkOutDays_to_customisedWorkOutNumberOfDays)
    }
}