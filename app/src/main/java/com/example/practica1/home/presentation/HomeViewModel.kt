package com.example.practica1.home.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica1.home.data.model.GetAllGoalsRequest
import com.example.practica1.home.data.model.GoalDTO
import com.example.practica1.home.domain.CompleteGoalUseCase
import com.example.practica1.home.domain.GetAllGoalsUseCase
import com.example.practica1.storage.GoalCompletedStorage
import com.example.practica1.storage.UserStorage
import kotlinx.coroutines.launch

class HomeViewModel (private val userStorage: UserStorage, private val goalCompletedStorage: GoalCompletedStorage) : ViewModel() {

    private val getAllGoalsUseCase = GetAllGoalsUseCase()
    private val completeGoalUseCase = CompleteGoalUseCase()
    private val _goals = MutableLiveData<List<GoalDTO>>(emptyList())
    val goals: LiveData<List<GoalDTO>> = _goals

    var goalCompleted: GoalDTO? = null

    private val _showConfirmModal = MutableLiveData<Boolean>()
    val showConfirmModal: LiveData<Boolean> = _showConfirmModal

    init {
        getAllGoals()
        Log.d("logs", goals.value.toString())
    }

    fun setShowConfirmModal (show: Boolean) {
        _showConfirmModal.value = show
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

    fun completeGoal ( goalId: String) {
        viewModelScope.launch {
            val result = completeGoalUseCase(goalId)
            result.onSuccess {
                data ->
                val goalsModified = _goals.value
                Log.d("API", "exito en la complecion")
                goalsModified?.map {
                    goal ->
                    if (goal.id == goalId) {
                        goal.text = data.data[0].text
                        goalCompleted = goal
                        goalCompletedStorage.saveGoal(goal)
                    }
                }
                _goals.value = goalsModified ?: emptyList()
                _showConfirmModal.value = true
            }
        }
    }

}