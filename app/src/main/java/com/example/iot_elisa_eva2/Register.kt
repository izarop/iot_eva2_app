package com.example.iot_elisa_eva2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        firebaseAuth = FirebaseAuth.getInstance()

        val name = findViewById<EditText>(R.id.etName)
        val email = findViewById<EditText>(R.id.etEmailReg)
        val pass = findViewById<EditText>(R.id.etPassReg)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val tvHaveAccount = findViewById<TextView>(R.id.tvHaveAccount)

        btnRegister.setOnClickListener {
            val nameText = name.text.toString().trim()
            val emailText = email.text.toString().trim()
            val passText = pass.text.toString().trim()

            if (nameText.isBlank() || emailText.isBlank() || passText.isBlank()) {
                showAlert("Error", "Completa todos los campos.")
            } else {
                firebaseAuth.createUserWithEmailAndPassword(emailText, passText)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()


                            startActivity(Intent(this, HomeActivity::class.java))
                            finish()
                        } else {
                            val errorMessage = task.exception?.message ?: "Error desconocido en el registro."
                            showAlert("Error de Registro", errorMessage)
                        }
                    }
            }
        }

        tvHaveAccount.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }

    private fun showAlert(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}