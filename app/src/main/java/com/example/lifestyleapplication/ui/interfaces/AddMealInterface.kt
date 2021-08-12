package com.example.lifestyleapplication.ui.interfaces

import com.example.lifestyleapplication.ui.models.addProd
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface AddMealInterface {
    @POST("mealplan/addmealplan.php")
    fun sendData(
        @Query("mealplan") mealplan: String,
        @Query("mealduration") mealduration: String,
        @Query("mealname") mealname: String,
        @Query("maxage") maxage: String,
        @Query("minage") minage: String,
        @Query("healthcondition") healthcondition: String,
        @Query("bodygoal") bodygoal: String,
        @Query("bodytype") bodytype: String,
        @Query("weight") weight: String,
        @Query("period") period: String,
        @Query("day") day: String,
        @Query("imgUrl") imgUrl: String
    ): Call<addProd>
}