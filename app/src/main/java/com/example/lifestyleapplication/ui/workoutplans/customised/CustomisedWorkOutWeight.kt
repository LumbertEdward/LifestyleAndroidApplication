package com.example.lifestyleapplication.ui.workoutplans.customised

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentCustomisedWorkOutWeightBinding

class CustomisedWorkOutWeight : Fragment() {
    private lateinit var binding: FragmentCustomisedWorkOutWeightBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCustomisedWorkOutWeightBinding.inflate(inflater, container, false)
        binding.floatingWeight.setOnClickListener {
            val weight = binding.editWeight.text.toString().trim()
            if (TextUtils.isEmpty(weight)){
                binding.editWeight.error = "Weight Needed"
            }
            else{
                val id = binding.radioGroupWeight.checkedRadioButtonId
                when(id){
                    R.id.radioKg -> {
                        val sharedPreferences: SharedPreferences = activity?.getSharedPreferences("CUSTOMIZEDWORKOUT", Context.MODE_PRIVATE)!!
                        val edt: SharedPreferences.Editor = sharedPreferences.edit()
                        edt.putString("WEIGHT", weight.toString())
                        edt.apply()
                        findNavController().navigate(R.id.action_customisedWorkOutWeight_to_customisedWorkOutHeight)
                    }
                    R.id.radioPounds -> {
                        val res = weight.toInt() / 2.20462
                        val sharedPreferences: SharedPreferences = activity?.getSharedPreferences("CUSTOMIZEDWORKOUT", Context.MODE_PRIVATE)!!
                        val edt: SharedPreferences.Editor = sharedPreferences.edit()
                        edt.putString("WEIGHT", res.toString())
                        edt.apply()
                        findNavController().navigate(R.id.action_customisedWorkOutWeight_to_customisedWorkOutHeight)
                    }
                    else -> {

                    }
                }
            }
        }
        setUsername()
        return binding.root
    }


    private fun setUsername() {
        val sharedPreferences = activity?.getSharedPreferences("USER", Context.MODE_PRIVATE)!!
        val username = sharedPreferences.getString("USERNAME", "")
        if (username != null){
            binding.introWeight.text = "What is your current weight " + username.toString() + "?"
        }

    }
}