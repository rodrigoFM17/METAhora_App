package com.example.practica1.home.presentation

import android.Manifest
import android.bluetooth.BluetoothDevice
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewModelScope
import com.example.practica1.home.data.model.GoalDTO
import com.example.state.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen ( homeViewModel: HomeViewModel, navigateToAddGoal: () -> Unit, navigateToPrintCompletedGoal: () -> Unit) {

    val context = LocalContext.current
    val logo = painterResource(R.drawable.logo)
    val goals: List<GoalDTO> by homeViewModel.goals.observeAsState(emptyList())
    val showConfirmModal: Boolean by homeViewModel.showConfirmModal.observeAsState(false)

    Column (
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(Color(0xFF2C2C2C))
            .padding( top = 50.dp, start = 20.dp, end = 20.dp)
    ) {
        Image(
            painter = logo,
            contentDescription = "logo de metahora",
        )
        Spacer( modifier = Modifier.height(20.dp))

        if ( showConfirmModal ){
            BasicAlertDialog(
                onDismissRequest = {  }
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column (
                        modifier = Modifier
                            .background(Color(0xFF3C3C3C))
                            .padding(30.dp)
                    ) {
                        Text(
                            text="Ha marcado una meta como completada ¿desea imprimir un recuerdo de su nuevo logro?",
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Row (
                            horizontalArrangement = Arrangement.Center
                        )
                        {
                            Button(
                                onClick = { homeViewModel.setShowConfirmModal(false)},
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF292929),
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text( text = "no", fontWeight = FontWeight.Bold, color = Color.White)
                            }

                            Spacer(modifier = Modifier.width(10.dp))
                            Button(
                                onClick = {
                                    homeViewModel.setShowConfirmModal(false)
                                    navigateToPrintCompletedGoal()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF068D9C),
                                ),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text( text = "si", fontWeight = FontWeight.Bold, color = Color.White)
                            }
                        }
                    }
                }
            }
        }

        Button(
            onClick = { navigateToAddGoal() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF068D9C),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "anadir meta")
        }
        Spacer( modifier = Modifier.height(20.dp))
        

        if ( goals.isNotEmpty() ) {
            for ( goal in goals ) {
                GoalCard(goal, homeViewModel)
            }
        } else {
            Text( text = "aun no tienes ninguna meta")
        }
    }
}

@Composable
fun GoalCard(goal: GoalDTO, homeViewModel: HomeViewModel) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF292929))
            .padding(20.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Text(
            text = goal.title,
            color = Color(0xFF068D9C),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer( modifier = Modifier.height(10.dp))
        Text (
            text = goal.description,
            color = Color(0xFF9A9A9A),
            fontSize = 20.sp
        )
        Spacer( modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                homeViewModel.viewModelScope.launch {
                    homeViewModel.completeGoal(goal.id)
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
            ),
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Canvas( modifier = Modifier.size(15.dp)) {
                    drawCircle( color = when (goal.text) {
                        "logrado" -> Color.Green
                        "pendiente" -> Color.Yellow
                        "fallido" -> Color.Red
                        else -> Color.Gray
                    })
                }
                Spacer( modifier = Modifier.width(10.dp))
                Text( text = goal.text, color = Color(0xFF575757), fontWeight = FontWeight.Bold)
            }
        }

    }
    Spacer( modifier = Modifier.height(20.dp))
}