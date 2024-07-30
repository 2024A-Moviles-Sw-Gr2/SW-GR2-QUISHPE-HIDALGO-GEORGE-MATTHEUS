package com.example.a03_recycleview_crunchyroll

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a03_recycleview_crunchyroll.utils.BaseDatosMemoria

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        inicializarRecyclerView()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun inicializarRecyclerView(){
        val rvRecomendaciones = findViewById<RecyclerView>(R.id.rv_recomendaciones)
        val rvInicio = findViewById<RecyclerView>(R.id.rv_inicio)

        val adaptadorRecomendaciones = RecyclerViewAdaptadorRecomendaciones(
            this,
            BaseDatosMemoria.listaRecomendaciones,
            rvRecomendaciones
        )
        val adaptadorInicio = RecyclerViewAdaptadorInicio(
            this,
            BaseDatosMemoria.inicio,
            rvInicio
        )
        rvRecomendaciones.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false)
        rvRecomendaciones.adapter = adaptadorRecomendaciones
        adaptadorRecomendaciones.notifyDataSetChanged()

        rvInicio.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false)
        rvInicio.adapter = adaptadorInicio
        adaptadorInicio.notifyDataSetChanged()
    }
}