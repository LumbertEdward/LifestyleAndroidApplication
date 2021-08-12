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
import com.example.lifestyleapplication.databinding.FragmentCustomisedWorkOutAgeBinding

class CustomisedWorkOutAge : Fragment() {
    private lateinit var binding: FragmentCustomisedWorkOutAgeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCustomisedWorkOutAgeBinding.inflate(inflater, container, false)
        binding.imgBack.setOnClickListener {

        }
        binding.floatingAge.setOnClickListener {
            setData()
        }
        setUsername()
        return binding.root
    }

    private fun setData() {
        if (binding.editAge.text.toString() != ""){
            val sharedPreferences: SharedPreferences = activity?.getSharedPreferences("CUSTOMIZEDWORKOUT", Context.MODE_PRIVATE)!!
            val edt: SharedPreferences.Editor = sharedPreferences.edit()
            edt.putString("AGE", binding.editAge.text.toString().trim())
            edt.apply()
            findNavController().navigate(R.id.action_customisedWorkOutAge_to_customisedWorkOutGender)
        }
        else{
            binding.editAge.error = "Enter Age"
        }

    }

    private fun setUsername() {
        val sharedPreferencesName = activity?.getSharedPreferences("USER", Context.MODE_PRIVATE)!!
        val username = sharedPreferencesName.getString("USERNAME", "")
        if (username != null){
            binding.introAge.text = "How old are you " + username.toString() + "?"
        }

    }
}