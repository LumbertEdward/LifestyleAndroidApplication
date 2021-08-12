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
import com.example.lifestyleapplication.databinding.FragmentCustomisedWorkOutBodyGoalsBinding

class CustomisedWorkOutBodyGoals : Fragment() {
    private lateinit var binding: FragmentCustomisedWorkOutBodyGoalsBinding
    private var gain: String? = ""
    private var lose: String? = ""
    private var maintain: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCustomisedWorkOutBodyGoalsBinding.inflate(inflater, container, false)
        binding.floatingBodyGoals.setOnClickListener {
            val plan: String = arguments?.getString("MEALPLAN").toString()
            val age: String = arguments?.getString("AGE").toString()
            val gender: String = arguments?.getString("GENDER").toString()
            val weight: String = arguments?.getString("WEIGHT").toString()
            val height = arguments?.getString("HEIGHT").toString()
            val type = arguments?.getString("BODYTYPE").toString()
            if (binding.optOne.isChecked){
                lose = binding.optOne.text.toString()
            }
            if (binding.optTwo.isChecked){
                gain = binding.optTwo.text.toString()
            }
            if (binding.optThree.isChecked){
                maintain = binding.optThree.text.toString()
            }

            val sharedPreferencesGend: SharedPreferences = activity?.getSharedPreferences("CUSTOMIZEDWORKOUT", Context.MODE_PRIVATE)!!
            val edt: SharedPreferences.Editor = sharedPreferencesGend.edit()
            edt.putString("LOSE", lose)
            edt.putString("GAIN", gain)
            edt.putString("MAINTAIN", maintain)
            edt.apply()
            findNavController().navigate(R.id.action_customisedWorkOutBodyGoals_to_customisedWorkOutDisability)
        }
        setUsername()
        return binding.root
    }

    private fun setUsername() {
        val sharedPreferences = activity?.getSharedPreferences("USER", Context.MODE_PRIVATE)!!
        val username = sharedPreferences.getString("USERNAME", "")
        if (username != null){
            binding.introBodyGoals.text = "What are your body goals " + username.toString() + "?"
        }

    }

}