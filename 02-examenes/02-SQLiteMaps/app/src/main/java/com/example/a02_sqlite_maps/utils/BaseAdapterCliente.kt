package com.example.a02_sqlite_maps.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.a02_sqlite_maps.R
import com.example.a02_sqlite_maps.model.entities.Cliente

class BaseAdapterCliente(
    contexto: Context,
    val listaCliente: ArrayList<Cliente>
) : BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(contexto)

    override fun getCount(): Int {
        return listaCliente.size
    }

    override fun getItem(position: Int): Any {
        return listaCliente[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = inflater.inflate(R.layout.activity_lv_cliente, parent, false)

        val tvid = view.findViewById<TextView>(R.id.tv_idCliente)
        val tvNombre = view.findViewById<TextView>(R.id.tv_nombre)
        val tvEmail = view.findViewById<TextView>(R.id.tv_email)
        val tvFechaRegistro = view.findViewById<TextView>(R.id.tv_fecha_registro)
        val tvActivo = view.findViewById<TextView>(R.id.tv_activo)

        val cliente = listaCliente[position]

        tvid.text = cliente.clienteID.toString()
        tvNombre.text = cliente.nombre
        tvEmail.text = cliente.email
        tvFechaRegistro.text = cliente.fechaRegistro
        tvActivo.text = cliente.activo

        return view
    }
}
