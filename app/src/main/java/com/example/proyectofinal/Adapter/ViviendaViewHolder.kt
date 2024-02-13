package com.example.proyectofinal.Adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectofinal.Vivienda
import com.example.proyectofinal.databinding.ItemViviendaBinding

// Clase ViewHolder para los elementos de vivienda en el RecyclerView
class ViviendaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Binding para acceder a los elementos de la vista
    val binding = ItemViviendaBinding.bind(view)

    // Método para renderizar los datos de una vivienda en la vista del ViewHolder
    fun render(viviendaModel: Vivienda) {
        // Establecer el nombre de la vivienda en el TextView correspondiente
        binding.tvNombre.text = viviendaModel.nombre
        // Establecer la descripción de la vivienda en el TextView correspondiente
        binding.tvDescripcion.text = viviendaModel.descripcion
        // Establecer el precio de la vivienda en el TextView correspondiente
        binding.tvPrecio.text = viviendaModel.precio

        // Aquí podrías agregar más lógica para cargar imágenes u otras acciones según sea necesario
    }
}
