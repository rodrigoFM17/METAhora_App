package com.example.practica1.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldL(
    value: String,
    onChange: ( String) -> Unit,
    placeholder: String,
    label: String,
    icon: (@Composable () -> Unit)?,
) {
    TextField(
        value = value,
        onValueChange = { onChange(it) },
        shape = RoundedCornerShape(5.dp),
        placeholder = { Text( text = placeholder) },
        label = { Text( text = label, fontWeight = FontWeight.Bold) },
        modifier = Modifier
            .fillMaxWidth(),
        leadingIcon = icon ?: {},
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
}