package com.example.lifestyleapplication.ui.addmeals

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentAddMealPlanBinding
import com.example.lifestyleapplication.ui.interfaces.AddMealInterface
import com.example.lifestyleapplication.ui.models.addProd
import com.example.lifestyleapplication.ui.retrofit.AddMealRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddMealPlanFragment : Fragment(), OnItemSelectedListener {
    private lateinit var binding: FragmentAddMealPlanBinding
    private val plans = arrayOf("Non-Vegan Meal Plan", "Full Vegan Meal Plan", "Lacto-Vegetarian Meal Plan", "Ovo-Vegetarian Meal Plan", "Lacto-Ovo Vegetarian Meal Plan", "Pescatarian Meal Plan")
    private val duration = arrayOf("Breakfast", "Lunch", "Dinner", "Dessert")
    private val bodyGoals = arrayOf("To lose weight", "To gain lean mass", "To maintain weight")
    private val bodyType = arrayOf("Endomorph", "Mesomorph", "Ectomorph")
    private val period = arrayOf("Normal", "Fasting")

    private var plan: String = "Non-Vegan Meal Plan"
    private var dur: String = "Breakfast"
    private var goal: String = "To lose weight"
    private var type: String = "Endomorph"
    private var per: String = "Normal"

    private lateinit var addMealInterface: AddMealInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddMealPlanBinding.inflate(inflater, container, false)
        binding.progressAddMeal.visibility = View.GONE
        var activity = activity as Context
        val planAdapter: ArrayAdapter<String> = ArrayAdapter(activity, android.R.layout.simple_spinner_item, plans)
        planAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinPlan.adapter = planAdapter
        binding.spinPlan.onItemSelectedListener = this

        val durationAdapter: ArrayAdapter<String> = ArrayAdapter(activity, android.R.layout.simple_spinner_item, duration)
        durationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinName.adapter = durationAdapter
        binding.spinName.onItemSelectedListener = this

        val typeAdapter: ArrayAdapter<String> = ArrayAdapter(activity, android.R.layout.simple_spinner_item, bodyType)
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.bodyTypeAdd.adapter = typeAdapter
        binding.bodyTypeAdd.onItemSelectedListener = this

        val goalsAdapter: ArrayAdapter<String> = ArrayAdapter(activity, android.R.layout.simple_spinner_item, bodyGoals)
        goalsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.bodyGoalAdd.adapter = goalsAdapter
        binding.bodyGoalAdd.onItemSelectedListener = this

        val periodAdapter: ArrayAdapter<String> = ArrayAdapter(activity, android.R.layout.simple_spinner_item, period)
        periodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.periodAdd.adapter = periodAdapter
        binding.periodAdd.onItemSelectedListener = this

        binding.cardAddMeal.setOnClickListener {
            binding.progressAddMeal.visibility = View.VISIBLE
            sendData()
        }

        return binding.root
    }

    private fun sendData() {
        var mealName = binding.mealName.text.toString().trim()
        var maxAge = binding.maximumAge.text.toString().trim()
        var minAge = binding.minimumAge.text.toString().trim()
        var healthCond = binding.healthConditionAdd.text.toString().trim()
        var weight = binding.weightAdd.text.toString().trim()
        var imgUrl = binding.imageAdd.text.toString().trim()
        var dy = binding.dayAdd.text.toString().trim()

        addMealInterface = AddMealRetrofit.getRetrofit().create(AddMealInterface::class.java)
        val call: Call<addProd> = addMealInterface.sendData(plan, dur, mealName, maxAge, minAge, healthCond, goal, type, weight, per, dy, imgUrl)
        call.enqueue(object : Callback<addProd>{
            override fun onResponse(call: Call<addProd>, response: Response<addProd>) {
                if (response.isSuccessful){
                    binding.progressAddMeal.visibility = View.GONE
                    Toast.makeText(activity, "Added", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<addProd>, t: Throwable) {
                binding.progressAddMeal.visibility = View.GONE
                Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when(p0!!.id){
            R.id.spinPlan -> {
                plan = plans[p2]
            }
            R.id.spinName -> {
                dur = duration[p2]
            }
            R.id.bodyGoalAdd -> {
                goal = bodyGoals[p2]
            }
            R.id.bodyTypeAdd -> {
                type = bodyType[p2]
            }
            else -> {

            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}