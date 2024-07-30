package com.example.a03_recycleview_crunchyroll

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a03_recycleview_crunchyroll.entities.Inicio

class RecyclerViewAdaptadorInicio (
    private val contexto: MainActivity,
    private val inicio: Inicio,
    private val recyclerView: RecyclerView

): RecyclerView.Adapter<RecyclerViewAdaptadorInicio.MyViewHolderInicio>(){

    inner class MyViewHolderInicio(
        view: View
    ): RecyclerView.ViewHolder(view){
        val idiomaTextView: TextView
        val descripcionTextView: TextView
        val imagenImageView: ImageView

        init {
            idiomaTextView = view.findViewById(R.id.tv_inicio_idioma)
            descripcionTextView = view.findViewById(R.id.tv_inicio_descripcion)
            imagenImageView = view.findViewById(R.id.iv_inicio_portada)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderInicio {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_inicio,
                parent,
                false
            )
        return MyViewHolderInicio(itemView)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: MyViewHolderInicio, position: Int) {
        holder.idiomaTextView.text = inicio.idioma
        holder.descripcionTextView.text = inicio.descripcion
        holder.imagenImageView.setImageResource(inicio.imagen)
    }
}