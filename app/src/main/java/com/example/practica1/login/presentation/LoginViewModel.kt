package com.example.practica1.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private var _email = MutableLiveData<String>()
    private var _password = MutableLiveData<String>()

    var email: LiveData<String> = _email
    var password: LiveData<String> = _password

    fun onChangeEmail(email: String) {
        _email.value = email
    }

    fun onChangePassword(password: String) {
        _password.value = password
    }
}