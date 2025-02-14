package com.example.practica1.addGoal.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.example.practica1.addGoal.data.model.AddGoalRequest
import com.example.practica1.login.data.model.LoginUserRequest
import com.example.practica1.storage.UserStorage
import com.example.state.R
import kotlinx.coroutines.launch

@Composable
fun AddGoalScreen ( addGoalViewModel: AddGoalViewModel, userStorage: UserStorage) {

    val title by addGoalViewModel.title.observeAsState("")
    val description by addGoalViewModel.description.observeAsState("")

    val logo = painterResource(R.drawable.logo)

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2C2C2C))
            .padding( top = 50.dp, start = 20.dp, end = 20.dp)
    ) {
        Image(
            painter = logo,
            contentDescription = "logo de metahora",
        )
        Spacer( modifier = Modifier.height(20.dp))

        TextField(
            value = title,
            onValueChange = { addGoalViewModel.onChangeTitle(it) },
            shape = RoundedCornerShape(5.dp),
            placeholder = { Text(text = "titulo de tu meta") },
            label = { Text(text = "Titulo", fontWeight = FontWeight.Bold) },
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF535353),
                focusedContainerColor = Color(0xFF535353),
                unfocusedLabelColor = Color(0xFFB6B6B6),
                focusedLabelColor = Color(0xFF068D9C),
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                focusedPlaceholderColor = Color(0xFFB6B6B6),
                focusedIndicatorColor = Color(0xFFB6B6B6)
            ),
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = description,
            onValueChange = { addGoalViewModel.onChangeDescription(it) },
            shape = RoundedCornerShape(5.dp),
            placeholder = { Text(text = "descripcion de tu meta") },
            label = { Text(text = "Descripcion", fontWeight = FontWeight.Bold) },
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF535353),
                focusedContainerColor = Color(0xFF535353),
                unfocusedLabelColor = Color(0xFFB6B6B6),
                focusedLabelColor = Color(0xFF068D9C),
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                focusedPlaceholderColor = Color(0xFFB6B6B6),
                focusedIndicatorColor = Color(0xFFB6B6B6)
            ),
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                addGoalViewModel.viewModelScope.launch {
                    val userId = userStorage.getId()
                    if (userId != null) {
                        val request = AddGoalRequest(userId , title, description)
                        addGoalViewModel.onClick(request)
                    }
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
                text = "Entrar",
                fontWeight = FontWeight.Bold
            )
        }


    }
}
