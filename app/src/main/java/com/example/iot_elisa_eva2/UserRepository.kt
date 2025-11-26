package com.example.iot_elisa_eva2

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Clase para manejar todas las operaciones relacionadas con la colección 'usuarios' de Firestore.
 */
class UserRepository {
    // Instancia de la base de datos Firestore y referencia a la colección 'usuarios'.
    private val db = FirebaseFirestore.getInstance()
    private val usersCollection = db.collection("usuarios")

    /**
     * Guarda el nombre, correo electrónico y fecha de registro del usuario en Firestore.
     * El UID de Firebase Authentication se usa como el ID del documento en Firestore.
     * * @param uid El ID único del usuario proporcionado por Firebase Auth.
     * @param name El nombre de usuario (etNuevoUsuario).
     * @param email El correo electrónico del usuario (etCorreo).
     */
    fun saveUser(uid: String, name: String, email: String) {
        val userMap = hashMapOf(
            "nombre_usuario" to name, // El campo 'nombre' que usaste en el XML
            "email" to email,
            "fechaRegistro" to Timestamp.now()
        )

        // Escribe el documento en la colección 'usuarios' usando el UID como ID.
        usersCollection.document(uid).set(userMap)
            .addOnSuccessListener {
                println("INFO: Usuario $uid guardado en Firestore exitosamente.")
            }
            .addOnFailureListener { e ->
                System.err.println("ERROR: Falló al guardar usuario $uid en Firestore: ${e.message}")
            }
    }
}