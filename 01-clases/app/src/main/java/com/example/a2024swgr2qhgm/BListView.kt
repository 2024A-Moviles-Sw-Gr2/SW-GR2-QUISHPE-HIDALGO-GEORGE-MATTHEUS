package com.example.a2024aswgr2vaes

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.example.a2024accgr1vaes.BBaseDatosMemoria
import com.example.a2024accgr1vaes.BEntrenador
import com.example.a2024swgr2qhgm.R
import com.google.android.material.snackbar.Snackbar

class BListView : AppCompatActivity() {
    val arreglo = BBaseDatosMemoria.arregloBEntrenador
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)
        val listView = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = ArrayAdapter(
            this, // contexto
            android.R.layout.simple_list_item_1, // layout xml a usar
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged() // ACTUALIZA UI
        val botonAnadirListView = findViewById<Button>(
            R.id.btn_aniadir_list_view
        )
        botonAnadirListView.setOnClickListener {
            anadirEntrenador(adaptador)
        }
        registerForContextMenu(listView) // NUEVA LINEA
    }// TERMINA ON CREATE

    var posicionItemSeleccionado = -1

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ){
        super.onCreateContextMenu(menu,v,menuInfo)
        // llenamos opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        // Obtener id
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
    }
    override fun onContextItemSelected(
        item: MenuItem
    ): Boolean {
        return when (item.itemId){
            R.id.mi_editar -> {
                mostrarSnackbar(
                    "Editar $posicionItemSeleccionado")
                return true
            }
            R.id.mi_eliminar -> {
                mostrarSnackbar(
                    "Eliminar $posicionItemSeleccionado")
                abrirDialogo()// NUEVA LINEA
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
    fun abrirDialogo(){}

    fun anadirEntrenador(
        adaptador: ArrayAdapter<BEntrenador>
    ){
        arreglo.add(
            BEntrenador(4,"Wendy","d@d.com")
        )
        adaptador.notifyDataSetChanged()
    }

    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.cl_ciclo_vida),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }
}