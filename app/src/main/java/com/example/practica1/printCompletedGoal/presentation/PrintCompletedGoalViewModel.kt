package com.example.practica1.printCompletedGoal.presentation

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practica1.core.hardware.BluetoothHelper
import com.example.practica1.storage.GoalCompletedStorage

class PrintCompletedGoalViewModel( private val goalCompletedStorage: GoalCompletedStorage, private val activity: Activity): ViewModel () {

    var socket: BluetoothSocket? = null

    private val _bluetoothDevices = MutableLiveData<Set<BluetoothDevice>>(emptySet<BluetoothDevice>())
    val bluetoothDevices: LiveData<Set<BluetoothDevice>> = _bluetoothDevices

    fun setPairedDevices (context: Context) {
        if(BluetoothHelper.checkAndRequestPermissions(activity)){
            _bluetoothDevices.value = BluetoothHelper.getPairedDevices(context)
            Log.d("Bluetooth", _bluetoothDevices.value?.size.toString())
        }
    }

    fun connectToPrinter (context: Context, device: BluetoothDevice) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
            socket = BluetoothHelper.connectToPrinter(device, context)
            Toast.makeText(context, "Conectado a ${device.name}", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Permiso de Bluetooth no concedido", Toast.LENGTH_SHORT).show()
        }
    }

    fun printGoal (context: Context) {
        val goalCompleted = goalCompletedStorage.getGoal()
        if (socket != null && goalCompleted != null) {
            BluetoothHelper.printGoal(socket, goalCompleted!!)
            Toast.makeText(context, "Impresión enviada", Toast.LENGTH_SHORT).show()
            BluetoothHelper.closeConnection(socket)
        } else {
            Toast.makeText(context, "No hay impresora conectada o no has completado ninguna meta", Toast.LENGTH_SHORT).show()
        }
    }
}