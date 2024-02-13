package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.proyectofinal.Adapter.ViviendaAdapter
import com.example.proyectofinal.databinding.ActivityListadoBinding
import com.example.proyectofinal.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.io.Console

class ListadoActivity : ActivityConMenus(){
    private lateinit var viviendaLista: ArrayList<Vivienda>
    private lateinit var Recycler: RecyclerView
    private lateinit var adapter: ViviendaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityListadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Agregar un TextChangedListener al EditText de filtro
        binding.filtro.addTextChangedListener { filtro ->
            val filtroVivienda = ViviendaProvider.viviendaList.filter { vivienda ->
                vivienda.nombre.lowercase().contains(filtro.toString().lowercase())
            }
            adapter.actualizarVivienda(filtroVivienda)
        }

        // Inicializar la lista de viviendas
        viviendaLista = ArrayList()

        // Configurar el RecyclerView
        Recycler = binding.Recycler
        Recycler.layoutManager = LinearLayoutManager(this)
        adapter = ViviendaAdapter(viviendaLista)
        Recycler.adapter = adapter



        // Cargar los datos desde Firestore
        cargarDatos()
    }

    private fun cargarDatos() {
        //Obtiene la instancia de la base de datos
        val db = FirebaseFirestore.getInstance()

        db.collection("Viviendas")
            .get()
            .addOnSuccessListener { cargar ->
                cargar.forEach { document ->
                    var viviend = document.toObject(Vivienda::class.java)
                    viviendaLista.add(viviend)
                    Log.d("Dato", viviend.nombre)
                       // print(viviendaLista.toString())
                }
                // Notificar al adaptador sobre el cambio en los datos
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.e("Cargar", "Error en la obtención de la vivienda", exception)
            }
    }


}