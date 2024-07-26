package com.example.a02_crud_gui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.a02_crud_gui.model.sql.BaseDeDatos
import com.example.a02_crud_gui.model.sql.SqliteHelper
import com.example.a02_crud_gui.view.FormClienteActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.cl_main_customer),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cliente)

        // Inicialización de Base de Datos
        BaseDeDatos.baseDeDatos = SqliteHelper(
            this
        )

        val botonCrearCliente = findViewById<Button>(R.id.btn_crear_cliente)
        botonCrearCliente.setOnClickListener{
            irActividad(FormClienteActivity::class.java)
        }
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this,clase)
        startActivity(intent)
    }
}