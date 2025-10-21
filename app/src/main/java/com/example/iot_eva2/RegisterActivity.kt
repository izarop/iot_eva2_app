package com.example.iot_eva2

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etUsuario = findViewById<EditText>(R.id.etNuevoUsuario)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etClave = findViewById<EditText>(R.id.etNuevaClave)
        val etConfirmar = findViewById<EditText>(R.id.etConfirmarClave)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarRegistro)

        btnGuardar.setOnClickListener {
            val usuario = etUsuario.text.toString().trim()
            val correo = etCorreo.text.toString().trim()
            val clave = etClave.text.toString()
            val confirmar = etConfirmar.text.toString()

            when {
                usuario.isEmpty() || correo.isEmpty() || clave.isEmpty() || confirmar.isEmpty() -> {
                    mostrarAlerta("Error", "Por favor, completa todos los campos.")
                }

                !android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches() -> {
                    mostrarAlerta("Correo inválido", "Ingresa un correo electrónico válido.")
                }

                clave.length < 6 -> {
                    mostrarAlerta("Contraseña débil", "La contraseña debe tener al menos 6 caracteres.")
                }

                clave != confirmar -> {
                    mostrarAlerta("No coinciden", "Las contraseñas no coinciden. Inténtalo de nuevo.")
                }

                else -> {
                    mostrarAlerta("Registro exitoso", "El usuario '$usuario' ha sido registrado correctamente (simulado).") {
                        finish() // vuelve al login
                    }
                }
            }
        }
    }

    private fun mostrarAlerta(titulo: String, mensaje: String, accionFinal: (() -> Unit)? = null) {
        AlertDialog.Builder(this)
            .setTitle(titulo)
            .setMessage(mensaje)
            .setPositiveButton("Aceptar") { dialog, _ ->
                dialog.dismiss()
                accionFinal?.invoke()
            }
            .show()
    }
}
