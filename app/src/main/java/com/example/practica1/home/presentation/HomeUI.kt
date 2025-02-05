package com.example.practica1.home.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practica1.home.data.model.GoalDTO
import com.example.state.R

@Composable
fun HomeScreen ( homeViewModel: HomeViewModel, navigateToAddGoal: () -> Unit) {

    Log.d("logs", "recomposicion HomeUI")
    val logo = painterResource(R.drawable.logo)
    val goals: List<GoalDTO> by homeViewModel.goals.observeAsState(emptyList())

//    LaunchedEffect(Unit) {
//        if (goals.isEmpty())
//        homeViewModel.getAllGoals()
//    }

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

        if ( goals.isNotEmpty() ) {
            for ( goal in goals ) {
                GoalCard(goal)
            }
        } else {
            Text( text = "aun no tienes ninguna meta")
        }
    }
}

@Composable
fun GoalCard(goal: GoalDTO) {
    Column (
        modifier = Modifier
            .background(Color(0xFF292929))
            .padding(10.dp)
    ) {
        Text(
            text = goal.title,
            color = Color(0xFF068D9C),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer( modifier = Modifier.height(10.dp))
        Text (
            text = goal.description,
            color = Color(0xFF9A9A9A),
            fontSize = 20.sp
        )
    }
    Spacer( modifier = Modifier.height(20.dp))
}