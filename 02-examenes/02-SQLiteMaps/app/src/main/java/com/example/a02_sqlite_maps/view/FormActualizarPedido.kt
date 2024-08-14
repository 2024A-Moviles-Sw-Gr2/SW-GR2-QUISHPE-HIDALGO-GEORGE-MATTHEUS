package com.example.a02_sqlite_maps.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.a02_sqlite_maps.R
import com.example.a02_sqlite_maps.model.sql.BaseDeDatos

class FormActualizarPedido : AppCompatActivity() {

    private lateinit var nombreForm: String
    private lateinit var pedidoID: String
    private lateinit var clienteID: String
    private lateinit var nombreCliente: String
    private lateinit var fechaPedido: String
    private lateinit var montoPedido: String
    private lateinit var descripcionPedido: String
    private lateinit var pagadoPedido: String
    private lateinit var latitudPed: String
    private lateinit var longitudPed: String

    @SuppressLint("MissingInflatedId", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_actualizar_pedido)

        nombreForm = intent.getStringExtra("nombreForm").toString()
        pedidoID = intent.getStringExtra("pedidoID").toString()
        clienteID = intent.getStringExtra("idCliente").toString()
        nombreCliente = intent.getStringExtra("nombreCliente").toString()
        fechaPedido = intent.getStringExtra("fechaPedido").toString()
        montoPedido = intent.getStringExtra("montoPedido").toString()
        descripcionPedido = intent.getStringExtra("descripcionPedido").toString()
        pagadoPedido = intent.getStringExtra("pagadoPedido").toString()
        latitudPed = intent.getStringExtra("latitudPedido").toString()
        longitudPed = intent.getStringExtra("longitudPedido").toString()

        val id = findViewById<TextView>(R.id.tv_actP_id)
        val fechaPed = findViewById<EditText>(R.id.input_actP_fechaPedido)
        val montoPed = findViewById<EditText>(R.id.input_actP_montoTotal)
        val descPed = findViewById<EditText>(R.id.input_actP_descripcion)
        val cbPagado = findViewById<CheckBox>(R.id.cb_actP_pagado)
        val latitudPedido = findViewById<EditText>(R.id.input_actP_latitud)
        val longitudPedido = findViewById<EditText>(R.id.input_actP_longitud)

        val pedido = BaseDeDatos.baseDeDatos!!
            .consultarPedidoPorID(pedidoID.toIntOrNull() ?: 0)

        id.text = "ID: $pedidoID"

        if (nombreForm == "FormSeleccionarUbiPedido") {
            fechaPed.setText(fechaPedido)
            montoPed.setText(montoPedido)
            descPed.setText(descripcionPedido)
            cbPagado.isChecked = pagadoPedido == "Si"
            latitudPedido.setText(latitudPed)
            longitudPedido.setText(longitudPed)
        }else{
            fechaPed.setText(pedido?.fechaPedido)
            montoPed.setText(pedido?.montoTotal.toString())
            descPed.setText(pedido?.descripcion)
            cbPagado.isChecked = pedido?.pagado == "Si"
            latitudPedido.setText(pedido?.ubicacion?.latitude.toString())
            longitudPedido.setText(pedido?.ubicacion?.longitude.toString())
        }



        val botonActualizarPedido = findViewById<Button>(R.id.btn_actualizar_pedido)
        botonActualizarPedido.setOnClickListener {
            val pedidoID: Int = pedidoID?.toIntOrNull() ?: 0
            // Obtener el estado del Switch y convertirlo a Si o No
            val pagado = if (cbPagado.isChecked) "Si" else "No"

            val respuesta = BaseDeDatos.baseDeDatos!!
                .actualizarPedido(
                    pedidoID,
                    fechaPed.text.toString(),
                    montoPed.text.toString().toDouble(),
                    pagado,
                    descPed.text.toString(),
                    latitudPedido.text.toString().toDouble(),
                    longitudPedido.text.toString().toDouble()
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

        val botonActualizarUbi = findViewById<ImageButton>(R.id.ib_actualizar_ubicacion_pedido)
        botonActualizarUbi.setOnClickListener {
            val intentAgregarUbicacionPedido = Intent(this, FormSeleccionarUbiPedido::class.java)
            intentAgregarUbicacionPedido.putExtra("accion", "actualizar")
            intentAgregarUbicacionPedido.putExtra("pedidoID", pedidoID)
            intentAgregarUbicacionPedido.putExtra("idCliente", clienteID)
            intentAgregarUbicacionPedido.putExtra("nombreCliente", nombreCliente)
            intentAgregarUbicacionPedido.putExtra("fechaPedido", fechaPed.text.toString())
            intentAgregarUbicacionPedido.putExtra("montoPedido", montoPed.text.toString())
            intentAgregarUbicacionPedido.putExtra("descripcionPedido", descPed.text.toString())
            intentAgregarUbicacionPedido.putExtra("pagadoPedido", if (cbPagado.isChecked) "Si" else "No")
            startActivity(intentAgregarUbicacionPedido)
        }

    }
}