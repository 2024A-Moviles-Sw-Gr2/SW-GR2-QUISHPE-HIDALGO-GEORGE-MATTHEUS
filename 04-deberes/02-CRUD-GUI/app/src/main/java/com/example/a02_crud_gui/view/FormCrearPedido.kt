package com.example.a02_crud_gui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.a02_crud_gui.R
import com.example.a02_crud_gui.model.sql.BaseDeDatos

class FormCrearPedido : AppCompatActivity() {

    private var clienteID: String? = null
    private var nombreCliente: String? = null

    @SuppressLint("MissingInflatedId", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_crear_pedido)

        clienteID = intent.getStringExtra("idCliente")
        nombreCliente = intent.getStringExtra("nombreCliente")

        val botonGuardarPedido = findViewById<Button>(R.id.btn_guardar_pedido)
        botonGuardarPedido.setOnClickListener {
            val fechaPed = findViewById<EditText>(R.id.input_crearP_fechaPedido)
            val montoPed = findViewById<EditText>(R.id.input_crearP_montoTotal)
            val descPed = findViewById<EditText>(R.id.input_crearP_descripcion)
            val cbPagado = findViewById<CheckBox>(R.id.cb_crearP_pagado)

            // Obtener el estado del Switch y convertirlo a Si o No
            val pagado = if (cbPagado.isChecked) "Si" else "No"

            val respuesta = BaseDeDatos.baseDeDatos!!
                .crearPedido(
                    clienteID?.toIntOrNull() ?: 0,
                    fechaPed.text.toString(),
                    montoPed.text.toString().toDouble(),
                    pagado,
                    descPed.text.toString()
                )

            val intentCrearPedido = Intent(this, MainPedidos::class.java)
            intentCrearPedido.putExtra("idCliente", clienteID)
            intentCrearPedido.putExtra("nombreCliente", nombreCliente)
            startActivity(intentCrearPedido)
        }

        val botonCancelarCliente = findViewById<Button>(R.id.btn_cancel_crear_pedido)
        botonCancelarCliente.setOnClickListener {
            val intentCrearPedido = Intent(this, MainPedidos::class.java)
            intentCrearPedido.putExtra("idCliente", clienteID)
            intentCrearPedido.putExtra("nombreCliente", nombreCliente)
            startActivity(intentCrearPedido)
        }
    }

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
