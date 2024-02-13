package com.example.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectofinal.databinding.ActivityEliminarViviendaBinding
import com.google.firebase.firestore.FirebaseFirestore

// Actividad para eliminar una vivienda
class EliminarViviendaActivity : ActivityConMenus() {
    lateinit var binding: ActivityEliminarViviendaBinding

    // Método llamado cuando se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el layout de la actividad usando ViewBinding
        binding = ActivityEliminarViviendaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener una instancia de Firebase Firestore
        val db = FirebaseFirestore.getInstance()

        // Configurar el OnClickListener para el botón de eliminar
        binding.bEliminar.setOnClickListener {
            // Obtener el ID de la vivienda a eliminar
            val idVivienda = binding.idViviendaDelete.text.toString()

            // Verificar que el campo ID de la vivienda no esté vacío
            if (idVivienda.isNotEmpty()) {
                // Referencia a la vivienda en la base de datos
                val viviendaRef = db.collection("Viviendas").document(idVivienda)

                // Eliminar la vivienda de la base de datos
                viviendaRef.delete()
                    .addOnSuccessListener {
                        // Mostrar un mensaje de éxito si la vivienda se eliminó correctamente
                        Toast.makeText(this, "Se ha eliminado la vivienda correctamente", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        // Mostrar un mensaje de error si la eliminación de la vivienda falló
                        Toast.makeText(this, "Error al eliminar la vivienda: $e", Toast.LENGTH_SHORT).show()
                    }
            } else {
                // Mostrar un mensaje si el campo ID de la vivienda está vacío
                Toast.makeText(this, "El campo ID de la vivienda está vacío", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
