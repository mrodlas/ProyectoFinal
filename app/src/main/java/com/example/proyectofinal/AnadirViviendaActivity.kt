package com.example.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectofinal.databinding.ActivityAnadirViviendaBinding
import com.google.firebase.firestore.FirebaseFirestore

class AnadirViviendaActivity : ActivityConMenus() {
    lateinit var binding: ActivityAnadirViviendaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnadirViviendaBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_anadir_vivienda)
        setContentView(binding.root)

        val db = FirebaseFirestore.getInstance()

        binding.bGuardar.setOnClickListener {
            if(binding.nombre.text.isNotEmpty() && binding.descripcion.text.isNotEmpty() &&
                binding.precio.text.isNotEmpty()){

                db.collection("Viviendas").document(binding.idVivienda.text.toString())
                    .set(mapOf(
                        "nombre" to binding.nombre.text.toString(),
                        "descripcion" to binding.descripcion.text.toString(),
                        "precio" to binding.precio.text.toString()
                    ))
                Toast.makeText(this, "Se han guardado los datos correctamente", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Algún campo está vacío", Toast.LENGTH_SHORT).show()
            }
        }
    }
}