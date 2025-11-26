package com.example.iot_elisa_eva2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

class AgregarNoticiaActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth // Variable para Firebase Auth
    private lateinit var etTitulo: EditText
    private lateinit var etResumen: EditText
    private lateinit var etContenido: EditText
    private lateinit var etAutor: EditText
    private lateinit var etUrlImagen: EditText
    private lateinit var btnCrearNoticia: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_noticia)

        // Inicializar Firestore y Auth
        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance() // Inicialización

        // ... (Inicialización de vistas)
        etTitulo = findViewById(R.id.etTitulo)
        etResumen = findViewById(R.id.etResumen)
        etContenido = findViewById(R.id.etContenido)
        etAutor = findViewById(R.id.etAutor)
        etUrlImagen = findViewById(R.id.etUrlImagen)
        btnCrearNoticia = findViewById(R.id.btnCrearNoticia)

        btnCrearNoticia.setOnClickListener {
            guardarNoticia()
        }
    }

    private fun guardarNoticia() {
        // Obtener el ID del usuario actual
        val currentUserId = auth.currentUser?.uid

        // Validar que el usuario esté logueado
        if (currentUserId == null) {
            showAlert("Error de Sesión", "Debes iniciar sesión para publicar una noticia.")
            return
        }

        val titulo = etTitulo.text.toString().trim()
        val resumen = etResumen.text.toString().trim()
        val contenido = etContenido.text.toString().trim()
        val autor = etAutor.text.toString().trim()
        val urlImagen = etUrlImagen.text.toString().trim()

        // Validaciones de campos
        if (titulo.isEmpty() || resumen.isEmpty() || contenido.isEmpty() || autor.isEmpty()) {
            showAlert("Error de Validación", "Todos los campos obligatorios.")
            return
        }

        // Crear el objeto Noticia (Requiere que Noticia.kt tenga 'userId')
        val nuevaNoticia = Noticia(
            titulo = titulo,
            resumen = resumen,
            contenido = contenido,
            autor = autor,
            urlImagen = urlImagen,
            fecha = Date(),
            userId = currentUserId // GUARDA EL ID DEL USUARIO
        )

        db.collection("noticias")
            .add(nuevaNoticia)
            .addOnSuccessListener {
                Toast.makeText(this, "Noticia creada con éxito.", Toast.LENGTH_LONG).show()
                finish()
            }
            .addOnFailureListener { e ->
                showAlert("Error Base de Datos", "No se pudo crear la noticia: ${e.message}")
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