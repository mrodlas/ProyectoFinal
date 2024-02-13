package com.example.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectofinal.databinding.ActivityActualizarViviendaBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Actividad para actualizar una vivienda
class ActualizarViviendaActivity : ActivityConMenus() {
    lateinit var binding: ActivityActualizarViviendaBinding

    // Método llamado cuando se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el layout de la actividad usando ViewBinding
        binding = ActivityActualizarViviendaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener una instancia de Firebase Firestore
        val db = FirebaseFirestore.getInstance()

        // Configurar el OnClickListener para el botón de actualización
        binding.bActualizar.setOnClickListener {
            val idVivienda = binding.idVivienda.text.toString()
            val nombre = binding.nombreVivienda.text.toString()
            val precio = binding.precioUpdate.text.toString()

            // Verificar que todos los campos estén completos
            if (idVivienda.isNotEmpty() && nombre.isNotEmpty() && precio.isNotEmpty()) {
                // Referencia a la vivienda en la base de datos
                val viviendaRef = db.collection("Viviendas").document(idVivienda)

                // Actualizar el precio de la vivienda en la base de datos
                viviendaRef.update("precio", precio.toDouble())
                    .addOnSuccessListener {
                        // Mostrar un mensaje de éxito si la actualización fue exitosa
                        Toast.makeText(this, "Se ha actualizado el precio correctamente", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        // Mostrar un mensaje de error si la actualización falló
                        Toast.makeText(this, "Error al actualizar el precio: $e", Toast.LENGTH_SHORT).show()
                    }
            } else {
                // Mostrar un mensaje si algún campo está vacío
                Toast.makeText(this, "Algún campo está vacío", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
