package com.example.practica1.login.presentation

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practica1.login.data.model.LoginUserRequest
import com.example.practica1.login.domain.LoginUserUseCase
import com.example.practica1.storage.UserStorage

class LoginViewModel( navigateToHome: () -> Unit, userStorage: UserStorage) : ViewModel() {
    private val loginUserUseCase = LoginUserUseCase()
    private var _email = MutableLiveData<String>()
    private var _password = MutableLiveData<String>()
    private val userStorage = userStorage
    private val navigateToHome = navigateToHome


    val email: LiveData<String> = _email
    val password: LiveData<String> = _password

    fun onChangeEmail(email: String) {
        _email.value = email
    }

    fun onChangePassword(password: String) {
        _password.value = password
    }

    suspend fun onClick(user: LoginUserRequest) {
        val result = loginUserUseCase(user)

        result.onSuccess {
            data ->
            userStorage.saveId(data.data[0].id)
            navigateToHome()
        }
    }
}