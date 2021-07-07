package com.example.lifestyleapplication.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentViewSelectedMealDurationBinding
import com.example.lifestyleapplication.ui.constants.constants
import com.example.lifestyleapplication.ui.interfaces.ViewMealInterface
import com.example.lifestyleapplication.ui.models.allMealDetails
import com.example.lifestyleapplication.ui.models.mealdetails
import com.example.lifestyleapplication.ui.models.selectedday
import com.example.lifestyleapplication.ui.retrofit.ViewMealRetrofit
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewSelectedMealDurationFragment : Fragment() {
    private lateinit var binding: FragmentViewSelectedMealDurationBinding
    private lateinit var viewMealInterface: ViewMealInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewSelectedMealDurationBinding.inflate(inflater, container, false)
        val animation = AnimationUtils.loadAnimation(activity, android.R.anim.fade_out)
        binding.cardDet.startAnimation(animation)

        binding.progressDet.visibility = View.VISIBLE
        binding.linearDet.visibility = View.GONE
        binding.imgBackSelected.setOnClickListener {
            findNavController().navigate(R.id.action_viewSelectedMealDurationFragment_to_mealPlanFragment)
        }
        val select: selectedday = arguments?.getParcelable("SELECTEDDAY")!!
        binding.txtMealSelected.text = select.meal
        val plan: String = arguments?.getString("PLAN").toString()

        getData(plan, select.meal, select.day, select.meal.toString().toLowerCase())
        return binding.root
    }

    private fun getData(plan: String, meal: String?, day: String?, dur: String?) {
        viewMealInterface = ViewMealRetrofit.getRetrofit().create(ViewMealInterface::class.java)
        val call: Call<allMealDetails> = viewMealInterface.getData(plan, meal!!, day!!, dur!!)
        call.enqueue(object: Callback<allMealDetails>{
            override fun onResponse(
                call: Call<allMealDetails>,
                response: Response<allMealDetails>
            ) {
                if (response.isSuccessful){
                    binding.progressDet.visibility = View.GONE
                    binding.linearDet.visibility = View.VISIBLE
                    showData(response.body()!!.data)
                }
            }

            override fun onFailure(call: Call<allMealDetails>, t: Throwable) {
                Toast.makeText(activity, t.message.toString(), Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun showData(data: ArrayList<mealdetails>) {
        val activity = activity as Context
        val picasso = Picasso.Builder(activity)
        picasso.downloader(OkHttp3Downloader(context))
        picasso.build().load(constants.DEVOTIONALS + data[0].image).into(binding.imgSelected)
        binding.mealSelected.text = data[0].name
        binding.typeSelected.text = data[0].bodyType
        binding.benefitSelected.text = data[0].bodyGoals
    }
}