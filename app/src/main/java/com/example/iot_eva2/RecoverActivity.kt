package com.example.iot_eva2

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class RecoverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover)

        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val btnEnviar = findViewById<Button>(R.id.btnEnviar)

        btnEnviar.setOnClickListener {
            val correo = etCorreo.text.toString().trim()

            if (correo.isEmpty()) {
                AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Por favor ingrese su correo electrónico.")
                    .setPositiveButton("OK", null)
                    .show()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Enlace enviado")
                    .setMessage("Se ha enviado un enlace de recuperación a $correo.")
                    .setPositiveButton("Aceptar", null)
                    .show()
            }
        }
    }
}
