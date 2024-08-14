package com.example.a02_sqlite_maps.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.a02_sqlite_maps.MainActivity
import com.example.a02_sqlite_maps.R
import com.example.a02_sqlite_maps.model.sql.BaseDeDatos
import com.example.a02_sqlite_maps.model.sql.SqliteHelper
import com.example.a02_sqlite_maps.utils.BaseAdapterPedido

class MainPedidos : AppCompatActivity() {

    private var clienteID: String? = null
    private var nombreCliente: String? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_pedidos)

        clienteID = intent.getStringExtra("idCliente")
        nombreCliente = intent.getStringExtra("nombreCliente")

        val nomCliente = findViewById<TextView>(R.id.tv_nombre_cl_pedidos)
        nomCliente.text = nombreCliente

        // Inicialización de Base de Datos
        BaseDeDatos.baseDeDatos = SqliteHelper(this)

        // Mostrar datos de la tabla en List View
        actualizarListView()

        // Ir a actividad para crear un Pedido
        val botonCrearPedido = findViewById<ImageButton>(R.id.btn_crear_pedido)
        botonCrearPedido.setOnClickListener{
            val intentCrearPedido = Intent(this, FormCrearPedido::class.java)
            intentCrearPedido.putExtra("idCliente", clienteID)
            intentCrearPedido.putExtra("nombreCliente", nombreCliente)
            startActivity(intentCrearPedido)
        }

        // Ir a actividad para ver los Clientes
        val botonVerClientes = findViewById<ImageButton>(R.id.btn_regresar_clientes)
        botonVerClientes.setOnClickListener{
            irActividad(MainActivity::class.java)
        }

        // Registrar para menú contextual
        val listaPedido = findViewById<ListView>(R.id.lv_pedido)
        registerForContextMenu(listaPedido)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos opciones del menú
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_pedido, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val selectedView = info.targetView
        val tvPedidoID = selectedView?.findViewById<TextView>(R.id.tv_idPedido)

        return when (item.itemId) {
            R.id.mi_pedido_actualizar -> {
                val intentActualizarP = Intent(this, FormActualizarPedido::class.java)
                intentActualizarP.putExtra("pedidoID", tvPedidoID?.text.toString())
                intentActualizarP.putExtra("idCliente", clienteID)
                intentActualizarP.putExtra("nombreCliente", nombreCliente)
                startActivity(intentActualizarP)
                true
            }
            R.id.mi_pedido_eliminar -> {
                BaseDeDatos.baseDeDatos!!.eliminarPedido(tvPedidoID?.text.toString().toInt())
                actualizarListView()
                true
            }
            R.id.mi_ver_ubicacion -> {
                val intentVerUbicacionP = Intent(
                    this,
                    FormVerUbiPedido::class.java
                )
                intentVerUbicacionP.putExtra("pedidoID", tvPedidoID?.text.toString())
                intentVerUbicacionP.putExtra("idCliente", clienteID)
                intentVerUbicacionP.putExtra("nombreCliente", nombreCliente)
                startActivity(intentVerUbicacionP)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun actualizarListView() {
        val listaPedido = findViewById<ListView>(R.id.lv_pedido)
        val arrayList = BaseDeDatos.baseDeDatos!!.consultarPedidosPorCliente(clienteID?.toIntOrNull() ?: 0)
        val baPedido = arrayList?.let { BaseAdapterPedido(this, it) }
        listaPedido.adapter = baPedido
        baPedido?.notifyDataSetChanged()
    }
}
