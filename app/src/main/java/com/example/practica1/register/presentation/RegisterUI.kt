package com.example.practica1.register.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.example.practica1.register.data.model.RegisterUserRequest
import com.example.state.R
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen (registerViewModel: RegisterViewModel) {
    val logo = painterResource(R.drawable.logo)
    val nickname by registerViewModel.nickname.observeAsState("")
    val email by registerViewModel.email.observeAsState("")
    val password by registerViewModel.password.observeAsState("")

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2C2C2C))
            .padding(20.dp)
    ) {
        Image(
            painter = logo,
            contentDescription = "logo de metahora",
        )
        Spacer(modifier = Modifier.height(40.dp))
        TextField(
            value = nickname,
            onValueChange = { registerViewModel.onChangeNickname(it) },
            shape = RoundedCornerShape(5.dp),
            placeholder = { Text(text = "") },
            label = { Text(text = "nombre de usuario", fontWeight = FontWeight.Bold) },
            modifier = Modifier
                .fillMaxWidth(),
            leadingIcon = {
                Icon(
                    Icons.Default.AccountCircle,
                    contentDescription = "nombre de usuario",
                    tint = Color(0xFFB6B6B6)
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF535353),
                focusedContainerColor = Color(0xFF535353),
                unfocusedLabelColor = Color(0xFFB6B6B6),
                focusedLabelColor = Color(0xFF068D9C),
                focusedTextColor = Color.White,
                focusedPlaceholderColor = Color(0xFFB6B6B6),
                focusedIndicatorColor = Color(0xFFB6B6B6)
            ),
        )
        Spacer(modifier = Modifier.height(50.dp))
        TextField(
            value = email,
            onValueChange = { registerViewModel.onChangeEmail(it) },
            shape = RoundedCornerShape(5.dp),
            placeholder = { Text(text = "ejemplo@gmail.com") },
            label = { Text(text = "Correo electronico", fontWeight = FontWeight.Bold) },
            modifier = Modifier
                .fillMaxWidth(),
            leadingIcon = {
                Icon(
                    Icons.Default.Email,
                    contentDescription = "correo electronico",
                    tint = Color(0xFFB6B6B6)
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF535353),
                focusedContainerColor = Color(0xFF535353),
                unfocusedLabelColor = Color(0xFFB6B6B6),
                focusedLabelColor = Color(0xFF068D9C),
                focusedTextColor = Color.White,
                focusedPlaceholderColor = Color(0xFFB6B6B6),
                focusedIndicatorColor = Color(0xFFB6B6B6)
            ),
        )
        Spacer(modifier = Modifier.height(50.dp))
        TextField(
            value = password,
            onValueChange = { registerViewModel.onChangePassword(it) },
            shape = RoundedCornerShape(5.dp),
            placeholder = { Text(text = "tlacuacho777") },
            label = { Text(text = "Contraseña", fontWeight = FontWeight.Bold) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(
                    Icons.Default.Lock,
                    contentDescription = "candado",
                    tint = Color(0xFFB6B6B6)
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF535353),
                focusedContainerColor = Color(0xFF535353),
                unfocusedLabelColor = Color(0xFFB6B6B6),
                focusedLabelColor = Color(0xFF068D9C),
                focusedTextColor = Color.White,
                focusedPlaceholderColor = Color(0xFFB6B6B6),
                focusedIndicatorColor = Color(0xFFB6B6B6)
            ),
        )
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = {
                val user = RegisterUserRequest(nickname, email, password)
                registerViewModel.viewModelScope.launch {
                    registerViewModel.onClick(user)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF068D9C)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF068D9C),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(
                text = "Registrarse",
                fontWeight = FontWeight.Bold
            )
        }
    }
}