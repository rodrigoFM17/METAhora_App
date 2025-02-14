package com.example.practica1.printCompletedGoal.presentation

import android.Manifest
import android.bluetooth.BluetoothDevice
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.practica1.components.BackgroundColumn
import com.example.practica1.components.TextL

@Composable
fun PrintCompletedGoalScreen (printCompletedGoalViewModel: PrintCompletedGoalViewModel) {

    val bluetoothDevices: Set<BluetoothDevice> by printCompletedGoalViewModel.bluetoothDevices.observeAsState(emptySet())
    val context = LocalContext.current

    if (bluetoothDevices.isEmpty()){
        printCompletedGoalViewModel.setPairedDevices(context)
        Log.d("Bluetooth", "buscanod dispositivos")
    }

    LaunchedEffect(Unit) {

    }

    BackgroundColumn {
        TextL(text = "Eliga una impresora Bluetooth")

        if(!bluetoothDevices.isNullOrEmpty()){
            bluetoothDevices!!.forEach { device ->
                Button(
                    onClick = {
                        printCompletedGoalViewModel.connectToPrinter(context, device)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3C3C3C),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    TextL(if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED)
                        device.name ?: "Desconocido"
                    else "Dispositivo Bluetooth"
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        } else {
            Spacer(modifier = Modifier.height(20.dp))
            TextL( text = "No hay dispositivos Bluetooth emparejados o no se cuenta con permisos")
        }

        Button(
            onClick = {printCompletedGoalViewModel.printGoal(context)},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF068D9C),
                contentColor = Color.White
            ),
        ) {
            Text( text = "Imprimir")
        }
    }
}