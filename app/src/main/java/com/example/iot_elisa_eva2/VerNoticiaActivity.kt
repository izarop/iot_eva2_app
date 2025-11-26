package com.example.iot_elisa_eva2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Locale
import com.example.eva2appiot.Noticia
import com.bumptech.glide.Glide

class VerNoticiaActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var tvTitulo: TextView
    private lateinit var tvAutorFecha: TextView
    private lateinit var tvResumen: TextView
    private lateinit var tvContenido: TextView
    private lateinit var ivImagen: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_noticia)

        db = FirebaseFirestore.getInstance()

        tvTitulo = findViewById(R.id.tvTituloDetalle)
        tvAutorFecha = findViewById(R.id.tvAutorFechaDetalle)
        tvResumen = findViewById(R.id.tvResumenDetalle)
        tvContenido = findViewById(R.id.tvContenidoDetalle)
        ivImagen = findViewById(R.id.ivNoticiaDetalle)

        val noticiaId = intent.getStringExtra("NOTICIA_ID")

        if (noticiaId != null) {
            cargarDetalleNoticia(noticiaId)
        } else {
            Toast.makeText(this, "Error: No se encontró el ID de la noticia.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun cargarDetalleNoticia(id: String) {
        db.collection("noticias").document(id)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val noticia = document.toObject(Noticia::class.java)

                    if (noticia != null) {
                        tvTitulo.text = noticia.titulo
                        tvResumen.text = noticia.resumen
                        tvContenido.text = noticia.contenido

                        val fechaStr = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(noticia.fecha ?: "")
                        tvAutorFecha.text = "Por ${noticia.autor ?: "Anónimo"} - $fechaStr"

                        // LÓGICA AÑADIDA PARA CARGAR LA IMAGEN CON GLIDE
                        if (!noticia.urlImagen.isNullOrEmpty()) {
                            Glide.with(this)
                                .load(noticia.urlImagen)
                                .placeholder(R.drawable.ic_launcher_background)
                                .error(R.drawable.ic_launcher_background)
                                .into(ivImagen)
                        } else {

                            ivImagen.setImageResource(R.drawable.ic_launcher_background)
                        }
                    } else {
                        Toast.makeText(this, "Error al mapear la noticia.", Toast.LENGTH_SHORT).show()}
                } else {
                    Toast.makeText(this, "Noticia no encontrada.", Toast.LENGTH_SHORT).show()}
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error de conexión: ${e.message}", Toast.LENGTH_SHORT).show()}
    }
}