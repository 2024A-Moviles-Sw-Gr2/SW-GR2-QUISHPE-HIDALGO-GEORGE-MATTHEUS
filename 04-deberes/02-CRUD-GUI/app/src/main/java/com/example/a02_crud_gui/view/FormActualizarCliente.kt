package com.example.a02_crud_gui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.a02_crud_gui.MainActivity
import com.example.a02_crud_gui.R
import com.example.a02_crud_gui.model.sql.BaseDeDatos

class FormActualizarCliente : AppCompatActivity() {

    private var clienteID: String? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_actualizar_cliente)

        val id = findViewById<TextView>(R.id.tv_actC_id)
        val nombre = findViewById<EditText>(R.id.input_actC_nombre)
        val email = findViewById<EditText>(R.id.input_actC_email)
        val fechaRegistro = findViewById<EditText>(R.id.input_actC_fechaRegistro)
        val cbActivo = findViewById<CheckBox>(R.id.cb_actC_activo)

        clienteID = intent.getStringExtra("clienteID")

        val cliente = BaseDeDatos.baseDeDatos!!
            .consultarClientePorID(clienteID?.toIntOrNull() ?: 0)

        id.text = clienteID
        nombre.setText(cliente?.nombre)
        email.setText(cliente?.email)
        fechaRegistro.setText(cliente?.fechaRegistro)
        cbActivo.isChecked = cliente?.activo == "Si"

        val botonActualizarCliente = findViewById<Button>(R.id.btn_actualizar_cliente)
        botonActualizarCliente.setOnClickListener {
            val clienteID: Int = clienteID?.toIntOrNull() ?: 0
            // Obtener el estado del Switch y convertirlo a Si o No
            val activo = if (cbActivo.isChecked) "Si" else "No"

            val respuesta = BaseDeDatos.baseDeDatos!!
                .actualizarCliente(
                    clienteID,
                    nombre.text.toString(),
                    email.text.toString(),
                    fechaRegistro.text.toString(),
                    activo,
                )
            irActividad(MainActivity::class.java)
        }

        val botonCancelarCliente = findViewById<Button>(R.id.btn_cancel_act_cliente)
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