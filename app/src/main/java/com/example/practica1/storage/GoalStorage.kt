package com.example.practica1.storage

import android.content.Context
import android.util.Log
import com.example.practica1.home.data.model.GoalDTO
import com.google.gson.Gson

class GoalCompletedStorage (private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("goal_completed", Context.MODE_PRIVATE)

    fun saveGoal(goal: GoalDTO) {
        sharedPreferences.edit().putString("goal_completed", Gson().toJson(goal)).apply()
    }

    fun getGoal(): GoalDTO {
        val goalString = sharedPreferences.getString("goal_completed", null)
        Log.d("GoalStorage", goalString.toString())
        val goalDTO = Gson().fromJson(goalString, GoalDTO::class.java)
        return goalDTO
    }
}