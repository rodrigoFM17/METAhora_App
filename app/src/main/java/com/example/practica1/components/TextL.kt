package com.example.practica1.components

import android.util.Size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun TextL (
    text: String = "Aceptar",
    color: Color = Color.White,
    fontWeight: FontWeight? = null,
    fontSize: TextUnit = 16.sp
) {
    Text(
        text = text,
        color = color,
        fontWeight = fontWeight,
        fontSize = fontSize
    )
}
