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
import com.example.lifestyleapplication.databinding.FragmentCustomisedWorkOutHeightBinding

class CustomisedWorkOutHeight : Fragment() {
    private lateinit var binding: FragmentCustomisedWorkOutHeightBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCustomisedWorkOutHeightBinding.inflate(inflater, container, false)
        binding.floatingHeight.setOnClickListener {
            val height = binding.editHeight.text.toString().trim()
            if (TextUtils.isEmpty(height)){
                binding.editHeight.error = "Weight Needed"
            }
            else{
                val id = binding.groupHeight.checkedRadioButtonId
                when(id){
                    R.id.radioCM -> {
                        val res = height.toInt() / 100
                        val sharedPreferences: SharedPreferences = activity?.getSharedPreferences("CUSTOMIZEDWORKOUT", Context.MODE_PRIVATE)!!
                        val edt: SharedPreferences.Editor = sharedPreferences.edit()
                        edt.putString("HEIGHT", res.toString())
                        edt.apply()
                        findNavController().navigate(R.id.action_customisedWorkOutHeight_to_customisedWorkOutBodyType)
                    }
                    R.id.radioMetres -> {
                        val sharedPreferences: SharedPreferences = activity?.getSharedPreferences("CUSTOMIZEDWORKOUT", Context.MODE_PRIVATE)!!
                        val edt: SharedPreferences.Editor = sharedPreferences.edit()
                        edt.putString("HEIGHT", height.toString())
                        edt.apply()
                        findNavController().navigate(R.id.action_customisedWorkOutHeight_to_customisedWorkOutBodyType)
                    }
                    R.id.radioFeet -> {
                        val res = height.toInt() * 0.3048
                        val sharedPreferences: SharedPreferences = activity?.getSharedPreferences("CUSTOMIZEDWORKOUT", Context.MODE_PRIVATE)!!
                        val edt: SharedPreferences.Editor = sharedPreferences.edit()
                        edt.putString("HEIGHT", res.toString())
                        edt.apply()
                        findNavController().navigate(R.id.action_customisedWorkOutHeight_to_customisedWorkOutBodyType)
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
            binding.introHeight.text = "What is your current Height " + username.toString() + "?"
        }
    }

}