package com.example.lifestyleapplication.ui.workoutplans

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentAddWorkOutPlanBinding
import com.example.lifestyleapplication.ui.models.addProd
import com.example.lifestyleapplication.ui.workoutplans.interfaces.AddWorkOutInterface
import com.example.lifestyleapplication.ui.workoutplans.retrofitclasses.AddWorkOutRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddWorkOutPlan : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentAddWorkOutPlanBinding
    private val section = arrayOf("Gym Workout Plan", "Home Workout Plan with equipment", "Home Workout Plan without equipment")
    private var sectionSelected: String = "Gym Workout Plan"
    private lateinit var addWorkOutInterface: AddWorkOutInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddWorkOutPlanBinding.inflate(inflater, container, false)
        binding.progressAddWorkOut.visibility = View.GONE
        var activity = activity as Context
        val sectionAdapter: ArrayAdapter<String> = ArrayAdapter(activity, android.R.layout.simple_spinner_item, section)
        sectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinSection.adapter = sectionAdapter
        binding.spinSection.onItemSelectedListener = this

        binding.cardAddWorkOut.setOnClickListener {
            binding.progressAddWorkOut.visibility = View.VISIBLE
            sendData()
        }
        return binding.root
    }

    private fun sendData() {
        var workoutName = binding.workoutNameAdd.text.toString().trim()
        var workoutDesc = binding.workoutDescriptionAdd.text.toString().trim()
        var workoutDay = binding.workoutDayAdd.text.toString().trim()
        var workoutNumber = binding.workoutNumberAdd.text.toString().trim()
        var workoutImage = binding.workoutImageAdd.text.toString().trim()

        addWorkOutInterface = AddWorkOutRetrofit.getRetrofit().create(AddWorkOutInterface::class.java)
        val call: Call<addProd> = addWorkOutInterface.sendData(workoutDay, workoutNumber, workoutName, workoutDesc, workoutImage, sectionSelected)
        call.enqueue(object : Callback<addProd>{
            override fun onResponse(call: Call<addProd>, response: Response<addProd>) {
                if (response.isSuccessful){
                    Toast.makeText(activity, "Added", Toast.LENGTH_LONG).show()
                    binding.progressAddWorkOut.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<addProd>, t: Throwable) {
                Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show()
                binding.progressAddWorkOut.visibility = View.GONE
            }
        })
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        sectionSelected = section[p2]
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

}