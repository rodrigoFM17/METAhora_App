package com.example.practica1.login.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica1.login.data.model.LoginUserRequest
import com.example.practica1.login.domain.LoginUserUseCase
import com.example.practica1.storage.UserStorage
import kotlinx.coroutines.launch

class LoginViewModel( private val navigateToHome: () -> Unit, private val userStorage: UserStorage) : ViewModel() {
    private val loginUserUseCase = LoginUserUseCase()
    private var _email = MutableLiveData<String>()
    private var _password = MutableLiveData<String>()


    val email: LiveData<String> = _email
    val password: LiveData<String> = _password

    fun onChangeEmail(email: String) {
        _email.value = email
    }

    fun onChangePassword(password: String) {
        _password.value = password
    }

    suspend fun onClick(user: LoginUserRequest) {
        viewModelScope.launch {
            val result = loginUserUseCase(user)

            result.onSuccess {
                    data -> (
                    if(data.success){
                        Log.d("API", "inicio sesion correcto")
                        userStorage.saveId(data.data[0].id)
                        navigateToHome()
                    } else {
                        Log.d("API", "fallo el login")
                    })
            }.onFailure {
                exception -> Log.d("API", "error en el resultado")
            }
        }


    }
}