package com.example.a02_sqlite_maps

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
import com.example.a02_sqlite_maps.model.sql.BaseDeDatos
import com.example.a02_sqlite_maps.model.sql.SqliteHelper
import com.example.a02_sqlite_maps.utils.BaseAdapterCliente
import com.example.a02_sqlite_maps.view.FormActualizarCliente
import com.example.a02_sqlite_maps.view.FormCrearCliente
import com.example.a02_sqlite_maps.view.MainPedidos

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización de Base de Datos
        BaseDeDatos.baseDeDatos = SqliteHelper(
            this
        )

        // Mostrar datos de la tabla en List View
        actualizarListView()

        // Ir a actividad para crear un Cliente
        val botonCrearCliente = findViewById<ImageButton>(R.id.btn_crear_cliente)
        botonCrearCliente.setOnClickListener{
            irActividad(FormCrearCliente::class.java)
        }

        // Registrar lista para menú contextual
        val listaCliente = findViewById<ListView>(R.id.lv_cliente)
        registerForContextMenu(listaCliente)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ){
        super.onCreateContextMenu(menu,v,menuInfo)
        // Llenamos opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_cliente, menu)
    }

    override fun onContextItemSelected(
        item: MenuItem
    ): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val selectedView = info.targetView
        val tvClienteID = selectedView.findViewById<TextView>(R.id.tv_idCliente)
        val tvClienteNombre = selectedView.findViewById<TextView>(R.id.tv_nombre)

        return when (item.itemId){
            R.id.mi_cliente_actualizar -> {
                val intentActualizarCl = Intent(
                    this,
                    FormActualizarCliente::class.java
                )
                intentActualizarCl.putExtra("clienteID", tvClienteID.text.toString())
                startActivity(intentActualizarCl)
                return true
            }

            R.id.mi_cliente_eliminar -> {
                BaseDeDatos.baseDeDatos!!.eliminarCliente(tvClienteID.text.toString().toInt())
                actualizarListView()
                return true
            }

            R.id.mi_cliente_ver_pedidos -> {
                val intentVerPedidos = Intent(
                    this,
                    MainPedidos::class.java
                )

                intentVerPedidos.putExtra("idCliente", tvClienteID.text.toString())
                intentVerPedidos.putExtra("nombreCliente", tvClienteNombre.text.toString())
                startActivity(intentVerPedidos)
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this,clase)
        startActivity(intent)
    }

    fun actualizarListView(){
        val listaCliente = findViewById<ListView>(R.id.lv_cliente)
        val arrayList = BaseDeDatos.baseDeDatos!!.consultarClientes()
        val baCliente = arrayList?.let { BaseAdapterCliente(this, it) }
        listaCliente.adapter = baCliente
        baCliente?.notifyDataSetChanged()
    }
}