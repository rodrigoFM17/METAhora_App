package com.example.practica1.register.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica1.register.data.model.RegisterUserRequest
import com.example.practica1.register.domain.RegisterUseCase
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {

    private val registerUseCase = RegisterUseCase()
    private var _nickname = MutableLiveData<String>()
    private var _email = MutableLiveData<String>()
    private var _password = MutableLiveData<String>()

    var nickname: LiveData<String> = _nickname
    var email: LiveData<String> = _email
    var password: LiveData<String> = _password

    fun onChangeNickname (nickname: String) {
        _nickname.value = nickname
    }

    fun onChangeEmail (email: String) {
        _email.value = email
    }

    fun onChangePassword (password: String) {
        _password.value = password
    }

    suspend fun onClick (userToRegister: RegisterUserRequest){
        viewModelScope.launch {
            Log.d("API", email.value + nickname.value + password.value )
            val result = registerUseCase(userToRegister)
            Log.d("API", result.toString())
            Log.d("API", result.isSuccess.toString())
        }
    }
}