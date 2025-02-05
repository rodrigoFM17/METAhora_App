package com.example.practica1.home.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica1.home.data.model.GetAllGoalsRequest
import com.example.practica1.home.data.model.GoalDTO
import com.example.practica1.home.domain.GetAllGoalsUseCase
import com.example.practica1.storage.UserStorage
import kotlinx.coroutines.launch

class HomeViewModel (private val userStorage: UserStorage) : ViewModel() {

    private val getAllGoalsUseCase = GetAllGoalsUseCase()
    private val _goals = MutableLiveData<List<GoalDTO>>(emptyList())
    val goals: LiveData<List<GoalDTO>> = _goals

    init {
        getAllGoals()
        Log.d("logs", goals.value.toString())
    }

    fun getAllGoals () {
        Log.d("API", "llamada a la funcion getALl Goals")
        viewModelScope.launch {
            val userId = userStorage.getId()
            if (userId != null) {
                val result = getAllGoalsUseCase(GetAllGoalsRequest(userId))
                Log.d("API", result.toString())
                result.onSuccess {
                        data ->
                    _goals.value = data.data
                }
            } else {
                Log.e("error", "no hay id de usuario")
            }

        }
    }

}