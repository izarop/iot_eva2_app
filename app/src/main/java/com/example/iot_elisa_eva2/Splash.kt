package com.example.iot_elisa_eva2

import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()

        val estadoBluetooth = when {
            bluetoothAdapter == null -> "Tu dispositivo no se puede conectar a Bluetooth."
            bluetoothAdapter.isEnabled -> "Bluetooth ACTIVADO."
            else -> "Bluetooth DESACTIVADO."
        }

        AlertDialog.Builder(this)
            .setTitle("Estado del Bluetooth")
            .setMessage("\n$estadoBluetooth")
            .setPositiveButton("Continuar") { dialog, _ ->
                dialog.dismiss()

                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this, Login::class.java))
                    finish()
                }, 1000)
            }
            .show()
    }
}
