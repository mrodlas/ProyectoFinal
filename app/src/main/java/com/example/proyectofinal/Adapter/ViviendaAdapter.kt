package com.example.proyectofinal.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.Vivienda
import com.example.proyectofinal.R

// Adaptador para la lista de viviendas
class ViviendaAdapter(private var viviendaList: List<Vivienda>) : RecyclerView.Adapter<ViviendaViewHolder>() {

    // Método que se llama cuando se necesita crear una nueva vista de elemento de vivienda
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViviendaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViviendaViewHolder(layoutInflater.inflate(R.layout.item_vivienda, parent, false))
    }

    // Método que devuelve el número total de elementos en la lista de viviendas
    override fun getItemCount(): Int {
        return viviendaList.size
    }

    // Método que se llama cuando se necesita mostrar datos en una vista de elemento de vivienda
    override fun onBindViewHolder(holder: ViviendaViewHolder, position: Int) {
        val item = viviendaList[position]
        holder.render(item)
    }

    // Método para actualizar la lista de viviendas y notificar al adaptador que los datos han cambiado
    fun actualizarVivienda(viviendaList: List<Vivienda>) {
        this.viviendaList = viviendaList
        notifyDataSetChanged()
    }
}
