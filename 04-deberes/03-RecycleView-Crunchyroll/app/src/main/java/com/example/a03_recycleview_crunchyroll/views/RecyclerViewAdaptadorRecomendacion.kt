package com.example.a03_recycleview_crunchyroll.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a03_recycleview_crunchyroll.MainActivity
import com.example.a03_recycleview_crunchyroll.R
import com.example.a03_recycleview_crunchyroll.entities.Recomendacion

class RecyclerViewAdaptadorRecomendacion (
    private val contexto: MainActivity,
    private val listaRecomendaciones: ArrayList<Recomendacion>,
    private val recyclerView: RecyclerView

): RecyclerView.Adapter<RecyclerViewAdaptadorRecomendacion.MyViewHolderRecomendacion>(){

    inner class MyViewHolderRecomendacion(
        view: View
    ): RecyclerView.ViewHolder(view){
        val nombreTextView: TextView
        val idiomaTextView: TextView
        val imagenImageView: ImageView

        init {
            nombreTextView = view.findViewById(R.id.tv_recomendacion_titulo)
            idiomaTextView = view.findViewById(R.id.tv_recomendacion_idioma)
            imagenImageView = view.findViewById(R.id.iv_recomendacion_portada)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderRecomendacion {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_recomendacion,
                parent,
                false
            )
        return MyViewHolderRecomendacion(itemView)
    }

    override fun getItemCount(): Int {
        return this.listaRecomendaciones.size
    }

    override fun onBindViewHolder(holder: MyViewHolderRecomendacion, position: Int) {
        val recomendacionActual = this.listaRecomendaciones[position]
        holder.nombreTextView.text = recomendacionActual.nombre
        holder.idiomaTextView.text = recomendacionActual.idiomas
        holder.imagenImageView.setImageResource(recomendacionActual.imagen)
    }

}