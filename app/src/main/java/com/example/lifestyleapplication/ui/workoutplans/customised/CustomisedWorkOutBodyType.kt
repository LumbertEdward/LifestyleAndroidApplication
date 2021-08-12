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
import com.example.lifestyleapplication.databinding.FragmentCustomisedWorkOutBodyTypeBinding

class CustomisedWorkOutBodyType : Fragment() {
    private lateinit var binding: FragmentCustomisedWorkOutBodyTypeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCustomisedWorkOutBodyTypeBinding.inflate(inflater, container, false)
        binding.floatingBodyType.setOnClickListener {
            val id = binding.radioBodyTypes.checkedRadioButtonId
            when(id){
                R.id.radioEndomorph -> {
                    val sharedPreferencesGend: SharedPreferences = activity?.getSharedPreferences("CUSTOMIZEDWORKOUT", Context.MODE_PRIVATE)!!
                    val edt: SharedPreferences.Editor = sharedPreferencesGend.edit()
                    edt.putString("BODYTYPE", binding.radioEndomorph.text.toString())
                    edt.apply()
                    findNavController().navigate(R.id.action_customisedWorkOutBodyType_to_customisedWorkOutBodyGoals)
                }
                R.id.radioMesomorph -> {
                    val sharedPreferencesGend: SharedPreferences = activity?.getSharedPreferences("CUSTOMIZEDWORKOUT", Context.MODE_PRIVATE)!!
                    val edt: SharedPreferences.Editor = sharedPreferencesGend.edit()
                    edt.putString("BODYTYPE", binding.radioMesomorph.text.toString())
                    edt.apply()
                    findNavController().navigate(R.id.action_customisedWorkOutBodyType_to_customisedWorkOutBodyGoals)
                }
                R.id.radioEctomorph -> {
                    val sharedPreferencesGend: SharedPreferences = activity?.getSharedPreferences("CUSTOMIZEDWORKOUT", Context.MODE_PRIVATE)!!
                    val edt: SharedPreferences.Editor = sharedPreferencesGend.edit()
                    edt.putString("BODYTYPE", binding.radioEctomorph.text.toString())
                    edt.apply()
                    findNavController().navigate(R.id.action_customisedWorkOutBodyType_to_customisedWorkOutBodyGoals)
                }
                else -> {

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
            binding.introBodyType.text = "What is your current body type " + username.toString() + "?"
        }
    }

}