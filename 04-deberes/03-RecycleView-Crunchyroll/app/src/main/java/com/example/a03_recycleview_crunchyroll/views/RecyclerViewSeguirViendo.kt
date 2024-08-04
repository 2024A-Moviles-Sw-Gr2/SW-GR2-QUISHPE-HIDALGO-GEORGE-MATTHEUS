package com.example.a03_recycleview_crunchyroll.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a03_recycleview_crunchyroll.MainActivity
import com.example.a03_recycleview_crunchyroll.R
import com.example.a03_recycleview_crunchyroll.entities.SeguirViendo

class RecyclerViewSeguirViendo (
    private val contexto: MainActivity,
    private val listaSeguirViendo: ArrayList<SeguirViendo>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<RecyclerViewSeguirViendo.MyViewHolderSeguirViendo>(){

    inner class MyViewHolderSeguirViendo(
        view: View
    ): RecyclerView.ViewHolder(view){
        val nombreTextView: TextView
        val temporadaTextView: TextView
        val minutosFaltantesTextView: TextView
        val imagenImageView: ImageView

        init {
            nombreTextView = view.findViewById(R.id.tv_seguir_viendo_titulo)
            temporadaTextView = view.findViewById(R.id.tv_seguir_viendo_temporada)
            minutosFaltantesTextView = view.findViewById(R.id.tv_seguir_viendo_tiempo_restante)
            imagenImageView = view.findViewById(R.id.iv_seguir_viendo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderSeguirViendo {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_seguir_viendo,
                parent,
                false
            )
        return MyViewHolderSeguirViendo(itemView)
    }

    override fun getItemCount(): Int {
        return this.listaSeguirViendo.size
    }

    override fun onBindViewHolder(holder: MyViewHolderSeguirViendo, position: Int) {
        val seguirViendoActual = this.listaSeguirViendo[position]
        holder.nombreTextView.text = seguirViendoActual.nombre
        holder.temporadaTextView.text = seguirViendoActual.temporada
        holder.minutosFaltantesTextView.text = seguirViendoActual.minutosFaltantes.toString() + " m restantes"
        holder.imagenImageView.setImageResource(seguirViendoActual.imagen)
    }

}