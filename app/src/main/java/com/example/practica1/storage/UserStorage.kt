package com.example.practica1.storage

import android.content.Context

class UserStorage (private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE)

    fun saveId (id: String) {
        sharedPreferences.edit().putString("user_id", id).apply()
    }

    fun getId(): String? {
        return sharedPreferences.getString("user_id", null)
    }

}