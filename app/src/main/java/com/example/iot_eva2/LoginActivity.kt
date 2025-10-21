package com.example.iot_eva2

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.iot_eva2.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etClave = findViewById<EditText>(R.id.etClave)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val btnRecuperar = findViewById<Button>(R.id.btnRecuperar)

        // BOTÓN LOGIN
        btnLogin.setOnClickListener {
            val usuario = etUsuario.text.toString()
            val clave = etClave.text.toString()

            if (usuario == "admin" && clave == "1234") {
                AlertDialog.Builder(this)
                    .setTitle("Inicio de sesión")
                    .setMessage("Inicio de sesión exitoso (simulado)")
                    .setPositiveButton("Aceptar", null)
                    .show()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Usuario o contraseña incorrectos")
                    .setPositiveButton("Intentar de nuevo", null)
                    .show()
            }
        }

        // BOTÓN REGISTRARSE
        btnRegistrar.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // BOTÓN RECUPERAR CLAVE
        btnRecuperar.setOnClickListener {
            val intent = Intent(this, RecoverActivity::class.java)
            startActivity(intent)
        }
    }
}
