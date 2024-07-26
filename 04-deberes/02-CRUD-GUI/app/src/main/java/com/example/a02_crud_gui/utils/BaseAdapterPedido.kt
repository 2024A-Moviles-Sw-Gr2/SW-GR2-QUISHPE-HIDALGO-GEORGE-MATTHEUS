package com.example.a02_crud_gui.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.a02_crud_gui.R
import com.example.a02_crud_gui.model.entities.Pedido

class BaseAdapterPedido (
    contexto: Context,
    val listaPedido: ArrayList<Pedido>
) : BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(contexto)

    override fun getCount(): Int {
        return listaPedido.size
    }

    override fun getItem(position: Int): Any {
        return listaPedido[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder", "MissingInflatedId")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = inflater.inflate(R.layout.activity_lv_pedidos, parent, false)

        val idPed = view.findViewById<TextView>(R.id.tv_idPedido)
        val fechaPed = view.findViewById<TextView>(R.id.tv_fecha_pedido)
        val totalPed = view.findViewById<TextView>(R.id.tv_monto_total)
        val estadoPed = view.findViewById<TextView>(R.id.tv_pagado)
        val descPed = view.findViewById<TextView>(R.id.tv_descripcion)

        val pedido = listaPedido[position]

        idPed.text = pedido.pedidoID.toString()
        fechaPed.text = pedido.fechaPedido
        totalPed.text = pedido.montoTotal.toString()
        estadoPed.text = pedido.pagado
        descPed.text = pedido.descripcion

        return view
    }
}
