package com.example.iot_elisa_eva2

import com.bumptech.glide.Glide
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.google.firebase.firestore.Exclude          // Para @Exclude
import com.google.firebase.firestore.IgnoreExtraProperties // Para @IgnoreExtraProperties

@IgnoreExtraProperties
data class Noticia(
    var userId: String? = "",
    var titulo: String? = "",
    // ... (otros campos)
    var autor: String? = "",
    var urlImagen: String? = "",
    var fecha: Date? = Date(),
    var userId: String? = "" // <--- CAMPO NUEVO
) {
    @Exclude
    @set:Exclude
    @get:Exclude
    var id: String? = null //identificacion del documento
}

typealias OnNoticiaClickListener = (Noticia) -> Unit

class Noticias(
    private val context: Context,
    private val noticias: MutableList<Noticia>,
    private val listener: OnNoticiaClickListener
) : RecyclerView.Adapter<com.example.iot_elisa_eva2.Noticias.NoticiaViewHolder>() {

    inner class NoticiaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTituloNoticia)
        val tvResumen: TextView = itemView.findViewById(R.id.tvResumenNoticia)

        val ivNoticia: ImageView = itemView.findViewById(R.id.ivNoticia)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_noticia, parent, false)
        return NoticiaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticiaViewHolder, position: Int) {
        val noticia = noticias[position]

        holder.tvTitulo.text = noticia.titulo

        val fechaFormateada = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(noticia.fecha ?: "")
        holder.tvResumen.text = "${noticia.resumen}\nPublicado: $fechaFormateada"

        val imageUrl = noticia.urlImagen
        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(context)
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.ivNoticia)
        } else {
            holder.ivNoticia.setImageResource(R.drawable.ic_launcher_background)
        }


        holder.itemView.setOnClickListener {
            listener(noticia)
        }
    }

    override fun getItemCount(): Int = noticias.size

    fun updateData(newNoticias: List<Noticia>) {
        noticias.clear()
        noticias.addAll(newNoticias)
        notifyDataSetChanged()
    }
