package com.example.lifestyleapplication.ui.workoutplans.customised

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentCustomisedWorkOutBinding
import com.example.lifestyleapplication.ui.constants.constants
import com.example.lifestyleapplication.ui.workoutplans.model.workoutmodel
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class CustomisedWorkOut : Fragment() {
    private lateinit var binding: FragmentCustomisedWorkOutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCustomisedWorkOutBinding.inflate(inflater, container, false)
        setData()
        return binding.root
    }

    private fun setData() {
        val exercise = arguments?.getParcelable<workoutmodel>("EXERCISE")
        val name = exercise!!.exercise
        val procedure = exercise.exercisedescription
        val img = exercise.exerciseimg

        binding.exercise.text = name
        binding.procedure.text = procedure

        val activity = activity as Context
        val picasso: Picasso.Builder = Picasso.Builder(activity)
        picasso.downloader(OkHttp3Downloader(activity))
        picasso.build().load(constants.DEVOTIONALS + img).into(binding.imgExercise)
    }

}