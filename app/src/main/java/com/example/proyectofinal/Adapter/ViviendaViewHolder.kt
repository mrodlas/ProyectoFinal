package com.example.proyectofinal.Adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectofinal.Vivienda
import com.example.proyectofinal.databinding.ItemViviendaBinding

class ViviendaViewHolder(view: View): RecyclerView.ViewHolder(view){
    val binding = ItemViviendaBinding.bind(view)

    fun render(viviendaModel: Vivienda){
        binding.tvNombre.text = viviendaModel.nombre
        binding.tvDescripcion.text = viviendaModel.descripcion
        binding.tvPrecio.text = viviendaModel.precio
    }
}