package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.Adapter.ViviendaAdapter
import com.example.proyectofinal.databinding.ActivityListadoBinding
import com.example.proyectofinal.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.io.Console

// Actividad para mostrar un listado de viviendas
class ListadoActivity : ActivityConMenus() {
    private lateinit var viviendaLista: ArrayList<Vivienda>
    private lateinit var Recycler: RecyclerView
    private lateinit var adapter: ViviendaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityListadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Agregar un TextChangedListener al EditText de filtro
        binding.filtro.addTextChangedListener { filtro ->
            // Filtrar la lista de viviendas según el texto ingresado en el EditText de filtro
            val filtroVivienda = ViviendaProvider.viviendaList.filter { vivienda ->
                vivienda.nombre.lowercase().contains(filtro.toString().lowercase())
            }
            // Actualizar el adaptador con la lista filtrada
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

    // Método para cargar los datos de las viviendas desde Firestore
    private fun cargarDatos() {
        // Obtener la instancia de la base de datos Firebase Firestore
        val db = FirebaseFirestore.getInstance()

        // Obtener la colección "Viviendas" de Firestore
        db.collection("Viviendas")
            .get()
            .addOnSuccessListener { cargar ->
                // Recorrer los documentos obtenidos de la colección "Viviendas"
                cargar.forEach { document ->
                    // Convertir cada documento en un objeto Vivienda
                    var vivienda = document.toObject(Vivienda::class.java)
                    // Agregar la vivienda a la lista de viviendas
                    viviendaLista.add(vivienda)
                    // Imprimir el nombre de la vivienda en el registro
                    Log.d("Dato", vivienda.nombre)
                }
                // Notificar al adaptador sobre el cambio en los datos
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                // Manejar el caso de error en la obtención de las viviendas
                Log.e("Cargar", "Error en la obtención de la vivienda", exception)
            }
    }
}
