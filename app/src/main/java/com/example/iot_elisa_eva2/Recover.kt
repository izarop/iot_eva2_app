package com.example.iot_elisa_eva2

import android.os.Bundle
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.content.Intent
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Recover : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover)

        auth = FirebaseAuth.getInstance()

        val tvVolverLogin = findViewById<TextView>(R.id.tvVolverLogin)
        tvVolverLogin.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            finish()
        }

        val emailEditText = findViewById<EditText>(R.id.etEmailRec)
        val btnSend = findViewById<Button>(R.id.btnSend)

        btnSend.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            enviarRecuperacionContrasena(email)
        }
    }

    private fun enviarRecuperacionContrasena(email: String) {
        if (email.isBlank() || !email.contains("@") || email.length < 5) {
            showAlert("Correo Inválido", "Por favor, ingresa un correo electrónico válido")
            return
        }

        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    showAlert(
                        "Correo Enviado",
                        "Hemos enviado un correo electrónico con las instrucciones para restablecer tu contraseña"
                    )


                } else {
                    showAlert(
                        "Error de Conexión",
                        "Hubo un problema al intentar enviar el correo"
                    )
                }
            }
    }

    private fun showAlert(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                if (title == "Correo Enviado") {
                    startActivity(Intent(this, Login::class.java))
                    finish()
                } else {
                    dialog.dismiss()
                }
            }
            .show()
    }
}
