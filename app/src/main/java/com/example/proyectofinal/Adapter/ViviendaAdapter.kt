package com.example.proyectofinal.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.Vivienda
import com.example.proyectofinal.R

class ViviendaAdapter (private var viviendaList: List<Vivienda>): RecyclerView.Adapter<ViviendaViewHolder>() {
   // interface OnItemClickListener {
   //     fun onItemClick(vivienda: Vivienda)
   // }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViviendaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViviendaViewHolder(layoutInflater.inflate(R.layout.item_vivienda, parent, false))
    }


    override fun getItemCount(): Int {
        return viviendaList.size
    }

    override fun onBindViewHolder(holder: ViviendaViewHolder, position: Int) {
        val item = viviendaList[position]
        holder.render(item)

    }


    fun actualizarVivienda(viviendaList: List<Vivienda>) {
        this.viviendaList = viviendaList
        notifyDataSetChanged()
    }
}