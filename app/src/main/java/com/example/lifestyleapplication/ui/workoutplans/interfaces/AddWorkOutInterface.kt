package com.example.lifestyleapplication.ui.workoutplans.interfaces

import com.example.lifestyleapplication.ui.models.addProd
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface AddWorkOutInterface {
    @POST("workoutplans/addworkout.php")
    fun sendData(
        @Query("workoutday") workoutday: String,
        @Query("workoutnumber") workoutnumber: String,
        @Query("workoutname") workoutname: String,
        @Query("workoutdescription") workoutdescription: String,
        @Query("workoutimage") workoutimage: String,
        @Query("workoutsection") workoutsection: String
    ): Call<addProd>
}