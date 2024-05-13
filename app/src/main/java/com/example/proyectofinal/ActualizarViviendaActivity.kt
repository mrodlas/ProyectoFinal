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

                // Verificar si la vivienda con el ID proporcionado existe
                viviendaRef.get().addOnSuccessListener { document ->
                    if (document.exists()) {
                        // El documento existe, se puede realizar la actualización
                    } else {
                        // El documento no existe, mostrar un mensaje de error
                        Toast.makeText(this, "La vivienda con el ID proporcionado no existe", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener { e ->
                    // Manejar errores al obtener el documento
                    Toast.makeText(this, "Error al obtener la vivienda: ", Toast.LENGTH_SHORT).show()
                }

                //Actualizar el nombre de la vivienda en la base de datos
                viviendaRef.update("nombre", nombre.toString())
                    .addOnSuccessListener {
                        //Mostrar un mensaje de éxito si la actualización fue exitosa
                        Toast.makeText(this, "Se ha actualizado el nombre correctamente", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        // Mostrar un mensaje de error si la actualización falló
                        Toast.makeText(this, "Error al actualizar el nombre: ", Toast.LENGTH_SHORT).show()
                    }

                // Actualizar el precio de la vivienda en la base de datos
                try {
                    val precioDouble = precio.toString()
                    // Actualizar el precio de la vivienda en la base de datos
                    viviendaRef.update("precio", precioDouble)
                        .addOnSuccessListener {
                            // Mostrar un mensaje de éxito si la actualización fue exitosa
                            Toast.makeText(this, "Se ha actualizado el precio correctamente", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { e ->
                            // Mostrar un mensaje de error si la actualización falló
                            Toast.makeText(this, "Error al actualizar el precio: ", Toast.LENGTH_SHORT).show()
                        }
                } catch (e: NumberFormatException) {
                    // Manejar la excepción si el valor ingresado no puede convertirse a Double
                    Toast.makeText(this, "El precio debe ser un número válido", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Mostrar un mensaje si algún campo está vacío
                Toast.makeText(this, "Algún campo está vacío", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
