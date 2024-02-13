package com.example.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectofinal.databinding.ActivityActualizarViviendaBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActualizarViviendaActivity : ActivityConMenus() {
    lateinit var binding: ActivityActualizarViviendaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityActualizarViviendaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = FirebaseFirestore.getInstance()

        binding.bActualizar.setOnClickListener {
            val idVivienda = binding.idVivienda.text.toString()
            val nombre = binding.nombreVivienda.text.toString()
            val precio = binding.precioUpdate.text.toString()

            if (idVivienda.isNotEmpty() && nombre.isNotEmpty() && precio.isNotEmpty()) {
                val viviendaRef = db.collection("Viviendas").document(idVivienda)
                viviendaRef.update("precio", precio.toDouble())
                    .addOnSuccessListener {
                        Toast.makeText(this, "Se ha actualizado el precio correctamente", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error al actualizar el precio: $e", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Algún campo está vacío", Toast.LENGTH_SHORT).show()
            }
        }
    }
}