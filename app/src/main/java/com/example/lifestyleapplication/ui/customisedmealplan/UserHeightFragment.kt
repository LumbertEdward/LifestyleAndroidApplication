package com.example.lifestyleapplication.ui.customisedmealplan

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentUserHeightBinding
import com.example.lifestyleapplication.ui.interfaces.generalinterface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class UserHeightFragment : Fragment() {
    private lateinit var binding: FragmentUserHeightBinding
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
        binding = FragmentUserHeightBinding.inflate(inflater, container, false)
        binding.imgBackHeight.setOnClickListener {
            findNavController().navigate(R.id.action_userHeightFragment_to_userCurrentWeightFragment)
        }
        binding.floatingHeight.setOnClickListener {
            val plan: String = arguments?.getString("MEALPLAN").toString()
            val age: String = arguments?.getString("AGE").toString()
            val gender: String = arguments?.getString("GENDER").toString()
            val weight: String = arguments?.getString("WEIGHT").toString()
            val height = binding.editHeight.text.toString().trim()
            if (TextUtils.isEmpty(height)){
                binding.editHeight.error = "Weight Needed"
            }
            else{
                val id = binding.groupHeight.checkedRadioButtonId
                when(id){
                    R.id.radioCM -> {
                        val res = height.toInt() / 100
                        general.sendCustomHeight(res.toString(), weight, gender, plan, age)
                    }
                    R.id.radioMetres -> {
                        general.sendCustomHeight(height, weight, gender, plan, age)
                    }
                    R.id.radioFeet -> {
                        val res = height.toInt() * 0.3048
                        general.sendCustomHeight(res.toString(), weight, gender, plan, age)
                    }
                    else -> {
                        general.sendCustomHeight(height, weight, gender, plan, age)
                    }
                }
            }

        }
        setUsername()
        return binding.root
    }

    private fun setUsername() {
        sharedPreferences = activity?.getSharedPreferences("USER", Context.MODE_PRIVATE)!!
        val username = sharedPreferences.getString("USERNAME", "")
        if (username != null){
            binding.introHeight.text = "What is your current Height " + username.toString() + "?"
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        general = context as generalinterface
    }
}