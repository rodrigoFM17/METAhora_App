package com.example.practica1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practica1.ui.theme.Practica1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Practica1Theme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    GreettingImage(stringResource(R.string.happy_birthday_message), "yo")
                }
            }
        }
    }
}

@Composable
fun GreettingImage(message: String, from: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.background)

    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier.weight(1f)
        ){
            Text(
                text = "hello mi nigga",
                modifier = Modifier.weight(1f).fillMaxSize()
            )
            Text(
                text = "hello mi nigga",
                modifier = Modifier.weight(1f).fillMaxSize()
            )
        }
        Row(
            modifier = Modifier.weight(1f)
        ){
            Text(
                text = "hello mi nigga",
                modifier = Modifier.weight(1f)
                    .fillMaxSize()
                    .background(Color.Cyan)
            )
            Text(
                text = "hello mi nigga",
                modifier = Modifier.weight(1f).fillMaxSize()
            )
        }

    }

}


@Preview(
    showBackground = true,
    name = "My preview"
)
@Composable
fun BirthCardPreview() {
    Practica1Theme {
        GreettingImage(message = "Hola mi nigga", from = "yo")
//        GreettingText(message = "Hola mi nigga", from = "yo")
    }
}