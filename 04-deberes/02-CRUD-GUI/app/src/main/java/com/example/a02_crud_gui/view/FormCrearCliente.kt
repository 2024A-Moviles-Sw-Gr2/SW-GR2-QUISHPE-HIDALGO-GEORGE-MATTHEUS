package com.example.a02_crud_gui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.a02_crud_gui.MainActivity
import com.example.a02_crud_gui.R
import com.example.a02_crud_gui.model.sql.BaseDeDatos

class FormCrearCliente : AppCompatActivity() {

    @SuppressLint("UseSwitchCompatOrMaterialCode", "MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_crear_cliente)

        val botonGuardarCliente = findViewById<Button>(R.id.btn_guardar_cliente)
        botonGuardarCliente.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.input_crearC_nombre)
            val email = findViewById<EditText>(R.id.input_crearC_email)
            val fechaRegistro = findViewById<EditText>(R.id.input_crearC_fechaRegistro)
            val cbActivo = findViewById<CheckBox>(R.id.cb_crearC_activo)

            // Obtener el estado del Switch y convertirlo a Si o No
            val activo = if (cbActivo.isChecked) "Si" else "No"

            val respuesta = BaseDeDatos.baseDeDatos!!
                .crearCliente(
                    nombre.text.toString(),
                    email.text.toString(),
                    fechaRegistro.text.toString(),
                    activo,
                )

            irActividad(MainActivity::class.java)
        }

        val botonCancelarCliente = findViewById<Button>(R.id.btn_cancel_crear_cliente)
        botonCancelarCliente.setOnClickListener{
            irActividad(MainActivity::class.java)
        }
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this,clase)
        startActivity(intent)
    }
}