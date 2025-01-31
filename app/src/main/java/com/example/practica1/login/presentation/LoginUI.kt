package com.example.practica1.login.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen () {

    Column {
        TextField(
            value = "",
            onValueChange = {},
            shape = RoundedCornerShape(5.dp),
            placeholder = { Text( text = "ejemplo@gmail.com") },
            label = { Text( text = "Correo electronico") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = "",
            onValueChange = {},
            shape = RoundedCornerShape(5.dp),
            placeholder = { Text( text = "tlacuacho777") },
            label = { Text( text = "Contraseña") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {},
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text( text = "Entrar")
        }
    }
}