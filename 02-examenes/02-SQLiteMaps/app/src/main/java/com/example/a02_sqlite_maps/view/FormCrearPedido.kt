package com.example.a02_sqlite_maps.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.a02_sqlite_maps.R
import com.example.a02_sqlite_maps.model.sql.BaseDeDatos

class FormCrearPedido : AppCompatActivity() {

    private var clienteID: String? = null
    private var nombreCliente: String? = null
    private var fechaPedido: String? = null
    private var montoPedido: String? = null
    private var descripcionPedido: String? = null
    private var pagadoPedido: String? = null
    private var latitud: String? = null
    private var longitud: String? = null

    @SuppressLint("MissingInflatedId", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_crear_pedido)

        clienteID = intent.getStringExtra("idCliente")
        nombreCliente = intent.getStringExtra("nombreCliente")
        fechaPedido = intent.getStringExtra("fechaPedido")
        montoPedido = intent.getStringExtra("montoPedido")
        descripcionPedido = intent.getStringExtra("descripcionPedido")
        pagadoPedido = intent.getStringExtra("pagadoPedido")
        latitud = intent.getStringExtra("latitudPedido")
        longitud = intent.getStringExtra("longitudPedido")

        val fechaPed = findViewById<EditText>(R.id.input_crearP_fechaPedido)
        val montoPed = findViewById<EditText>(R.id.input_crearP_montoTotal)
        val descPed = findViewById<EditText>(R.id.input_crearP_descripcion)
        val cbPagado = findViewById<CheckBox>(R.id.cb_crearP_pagado)
        val latitudPedido = findViewById<EditText>(R.id.input_crearP_latitud)
        val longitudPedido = findViewById<EditText>(R.id.input_crearP_longitud)

        fechaPed.setText(fechaPedido)
        montoPed.setText(montoPedido)
        descPed.setText(descripcionPedido)
        cbPagado.isChecked = pagadoPedido == "Si"
        latitudPedido.setText(latitud)
        longitudPedido.setText(longitud)

        val botonGuardarPedido = findViewById<Button>(R.id.btn_guardar_pedido)
        botonGuardarPedido.setOnClickListener {

            // Obtener el estado del Switch y convertirlo a Si o No
            val pagado = if (cbPagado.isChecked) "Si" else "No"

            val respuesta = BaseDeDatos.baseDeDatos!!
                .crearPedido(
                    clienteID?.toIntOrNull() ?: 0,
                    fechaPed.text.toString(),
                    montoPed.text.toString().toDouble(),
                    pagado,
                    descPed.text.toString(),
                    latitudPedido.text.toString().toDouble(),
                    longitudPedido.text.toString().toDouble()
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

        val botonAgregarUbi = findViewById<ImageButton>(R.id.ib_agregar_ubicacion_pedido)
        botonAgregarUbi.setOnClickListener {
            val intentAgregarUbicacionPedido = Intent(this, FormSeleccionarUbiPedido::class.java)
            intentAgregarUbicacionPedido.putExtra("accion", "crear")
            intentAgregarUbicacionPedido.putExtra("idCliente", clienteID)
            intentAgregarUbicacionPedido.putExtra("nombreCliente", nombreCliente)
            intentAgregarUbicacionPedido.putExtra("fechaPedido", fechaPed.text.toString())
            intentAgregarUbicacionPedido.putExtra("montoPedido", montoPed.text.toString())
            intentAgregarUbicacionPedido.putExtra("descripcionPedido", descPed.text.toString())
            intentAgregarUbicacionPedido.putExtra("latitudPedido", latitudPedido.text.toString())
            intentAgregarUbicacionPedido.putExtra("longitudPedido", longitudPedido.text.toString())
            intentAgregarUbicacionPedido.putExtra("pagadoPedido", if (cbPagado.isChecked) "Si" else "No")
            startActivity(intentAgregarUbicacionPedido)
        }
    }
}
