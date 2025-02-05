package com.example.practica1.addGoal.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practica1.addGoal.data.model.AddGoalRequest
import com.example.practica1.addGoal.domain.AddGoalUseCase

class AddGoalViewModel(private val navigateToHome: () -> Unit) : ViewModel() {

    private val addGoalUseCase = AddGoalUseCase()
    private val _title = MutableLiveData<String>()
    private val _description = MutableLiveData<String>()
    private val _failedOperation = MutableLiveData<Boolean>(false)

    val failedOperation: LiveData<Boolean> = _failedOperation
    val title: LiveData<String> = _title
    val description: LiveData<String> = _description

    fun onChangeTitle (title : String) {
        _title.value = title
    }

    fun onChangeDescription (description : String) {
        _description.value = description
    }

    suspend fun onClick ( request : AddGoalRequest) {
        val result = addGoalUseCase(request)
        if (result.isSuccess) {
            navigateToHome()
        } else {
            _failedOperation.value = true
        }

    }

}