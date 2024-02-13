package com.example.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectofinal.databinding.ActivityAnadirViviendaBinding
import com.example.proyectofinal.databinding.ActivityEliminarViviendaBinding
import com.google.firebase.firestore.FirebaseFirestore

class EliminarViviendaActivity : ActivityConMenus() {
    lateinit var binding: ActivityEliminarViviendaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEliminarViviendaBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_anadir_vivienda)
        setContentView(binding.root)

        val db = FirebaseFirestore.getInstance()

        binding.bEliminar.setOnClickListener {
            val idVivienda = binding.idViviendaDelete.text.toString()

            if (idVivienda.isNotEmpty()) {
                val viviendaRef = db.collection("Viviendas").document(idVivienda)
                viviendaRef.delete()
                    .addOnSuccessListener {
                        Toast.makeText(this, "Se ha eliminado la vivienda correctamente", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error al eliminar la vivienda: $e", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "El campo ID de la vivienda está vacío", Toast.LENGTH_SHORT).show()
            }
        }
    }

}