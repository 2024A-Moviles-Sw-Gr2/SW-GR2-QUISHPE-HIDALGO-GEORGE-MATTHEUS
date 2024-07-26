package com.example.a02_crud_gui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a02_crud_gui.R
import com.example.a02_crud_gui.model.sql.BaseDeDatos

class FormActualizarPedido : AppCompatActivity() {

    private var pedidoID: String? = null
    private var clienteID: String? = null
    private var nombreCliente: String? = null

    @SuppressLint("MissingInflatedId", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_actualizar_pedido)

        pedidoID = intent.getStringExtra("pedidoID")
        clienteID = intent.getStringExtra("idCliente")
        nombreCliente = intent.getStringExtra("nombreCliente")

        val id = findViewById<TextView>(R.id.tv_actP_id)
        val fechaPed = findViewById<TextView>(R.id.input_actP_fechaPedido)
        val montoPed = findViewById<TextView>(R.id.input_actP_montoTotal)
        val descripcionPed = findViewById<TextView>(R.id.input_actP_descripcion)
        val estadoPed = findViewById<CheckBox>(R.id.cb_actP_pagado)

        val pedido = BaseDeDatos.baseDeDatos!!
            .consultarPedidoPorID(pedidoID?.toIntOrNull() ?: 0)

        id.text = "ID: $pedidoID"
        fechaPed.text = pedido?.fechaPedido
        montoPed.text = pedido?.montoTotal.toString()
        descripcionPed.text = pedido?.descripcion
        estadoPed.isChecked = pedido?.pagado == "Si"

        val botonActualizarPedido = findViewById<Button>(R.id.btn_actualizar_pedido)
        botonActualizarPedido.setOnClickListener {
            val pedidoID: Int = pedidoID?.toIntOrNull() ?: 0
            // Obtener el estado del Switch y convertirlo a Si o No
            val pagado = if (estadoPed.isChecked) "Si" else "No"

            val respuesta = BaseDeDatos.baseDeDatos!!
                .actualizarPedido(
                    pedidoID,
                    fechaPed.text.toString(),
                    montoPed.text.toString().toDouble(),
                    pagado,
                    descripcionPed.text.toString(),
                )

            val intentVerPedidos = Intent(this, MainPedidos::class.java)
            intentVerPedidos.putExtra("idCliente", clienteID)
            intentVerPedidos.putExtra("nombreCliente", nombreCliente)
            startActivity(intentVerPedidos)

        }

        val botonCancelarPedido = findViewById<Button>(R.id.btn_cancel_act_pedido)
        botonCancelarPedido.setOnClickListener{
            val intentVerPedidos = Intent(this, MainPedidos::class.java)
            intentVerPedidos.putExtra("idCliente", clienteID)
            intentVerPedidos.putExtra("nombreCliente", nombreCliente)
            startActivity(intentVerPedidos)
        }

    }
}