package com.example.practica1.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.state.R

@Composable
fun BackgroundColumn (content: @Composable () -> Unit) {

    val logo = painterResource(R.drawable.logo)

    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFF2C2C2C))
            .padding(20.dp)
    ) {
        Image(
            painter = logo,
            contentDescription = "logo de metahora",
        )
        Spacer( modifier = Modifier.height(20.dp))
        content()
    }
}