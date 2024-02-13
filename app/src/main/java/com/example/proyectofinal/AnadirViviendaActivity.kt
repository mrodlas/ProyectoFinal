package com.example.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectofinal.databinding.ActivityAnadirViviendaBinding
import com.google.firebase.firestore.FirebaseFirestore

// Actividad para añadir una nueva vivienda
class AnadirViviendaActivity : ActivityConMenus() {
    lateinit var binding: ActivityAnadirViviendaBinding

    // Método llamado cuando se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el layout de la actividad usando ViewBinding
        binding = ActivityAnadirViviendaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener una instancia de Firebase Firestore
        val db = FirebaseFirestore.getInstance()

        // Configurar el OnClickListener para el botón de guardar
        binding.bGuardar.setOnClickListener {
            // Verificar que todos los campos estén completos
            if (binding.nombre.text.isNotEmpty() && binding.descripcion.text.isNotEmpty() &&
                binding.precio.text.isNotEmpty()) {
                // Guardar los datos de la nueva vivienda en la base de datos Firestore
                db.collection("Viviendas").document(binding.idVivienda.text.toString())
                    .set(mapOf(
                        "nombre" to binding.nombre.text.toString(),
                        "descripcion" to binding.descripcion.text.toString(),
                        "precio" to binding.precio.text.toString()
                    ))

                // Mostrar un mensaje de éxito si los datos se guardaron correctamente
                Toast.makeText(this, "Se han guardado los datos correctamente", Toast.LENGTH_SHORT).show()
            } else {
                // Mostrar un mensaje si algún campo está vacío
                Toast.makeText(this, "Algún campo está vacío", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
