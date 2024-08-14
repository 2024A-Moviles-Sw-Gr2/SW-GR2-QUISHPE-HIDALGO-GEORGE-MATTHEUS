package com.example.a02_sqlite_maps.view

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.a02_sqlite_maps.R
import com.example.a02_sqlite_maps.model.sql.BaseDeDatos
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar

class FormVerUbiPedido : AppCompatActivity() {

    private lateinit var mapa: GoogleMap
    private lateinit var ubicacionPedido: LatLng
    private var pedidoID: String? = null
    var permisos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_ver_ubicacion_pedido)

        pedidoID = intent.getStringExtra("pedidoID")
        ubicacionPedido = BaseDeDatos.baseDeDatos!!
            .consultarUbicacionPorID(pedidoID?.toIntOrNull() ?: 0)!!

        solicitarPermisos()
        iniciarLogicaMapa()

        val botonVerUbicacion = findViewById<Button>(R.id.btn_ir_ubicacion)
        botonVerUbicacion.setOnClickListener {
            val zoom = 17f
            if (ubicacionPedido != null) {
                moverCamaraConZoom(ubicacionPedido, zoom)
            }
        }

        val botonVolver = findViewById<ImageButton>(R.id.btn_regresar_ver_ubi_form_anterior)
        botonVolver.setOnClickListener{
            val intentMainPedidos = Intent(
                this,
                MainPedidos::class.java
            )
            intentMainPedidos.putExtra("idCliente", intent.getStringExtra("idCliente"))
            intentMainPedidos.putExtra("nombreCliente", intent.getStringExtra("nombreCliente"))
            startActivity(intentMainPedidos)
        }
    }

    fun solicitarPermisos() {
        val contexto = this.applicationContext
        val nombrePermisoFine = android.Manifest.permission.ACCESS_FINE_LOCATION
        val nombrePermisoCoarse = android.Manifest.permission.ACCESS_COARSE_LOCATION
        val permisoFine = ContextCompat.checkSelfPermission(contexto, nombrePermisoFine)
        val permisoCoarse = ContextCompat.checkSelfPermission(contexto, nombrePermisoCoarse)
        val tienePermisos = permisoFine == PackageManager.PERMISSION_GRANTED &&
                permisoCoarse == PackageManager.PERMISSION_GRANTED

        if (tienePermisos){
            permisos = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(nombrePermisoFine, nombrePermisoCoarse),
                1
            )
        }
    }

    fun establecerConfiguracionMapa() {
        val contexto = this.applicationContext
        with(mapa){
            val nombrePermisoFine = android.Manifest.permission.ACCESS_FINE_LOCATION
            val nombrePermisoCoarse = android.Manifest.permission.ACCESS_COARSE_LOCATION
            val permisoFine = ContextCompat.checkSelfPermission(contexto, nombrePermisoFine)
            val permisoCoarse = ContextCompat.checkSelfPermission(contexto, nombrePermisoCoarse)
            val tienePermisos = permisoFine == PackageManager.PERMISSION_GRANTED &&
                    permisoCoarse == PackageManager.PERMISSION_GRANTED
            if (tienePermisos){
                mapa.isMyLocationEnabled = true
                mapa.uiSettings.isMyLocationButtonEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
        }
    }

    fun iniciarLogicaMapa(){
        val fragmentoMapa = supportFragmentManager
            .findFragmentById(R.id.map_pedido_ver_ubicacion) as com.google.android.gms.maps.SupportMapFragment
        fragmentoMapa.getMapAsync {
                googleMap ->
            with(googleMap){
                mapa = googleMap
                establecerConfiguracionMapa()

                anadirMarcador(ubicacionPedido, "Ubicación del pedido")
                moverCamaraConZoom(ubicacionPedido, 17f)
                escucharListeners()
            }
        }
    }

    fun  escucharListeners(){
        mapa.setOnMarkerClickListener {
            mostrarSnackbar("Ubicación del pedido")
            return@setOnMarkerClickListener true
        }

    }

    fun moverCamaraConZoom(latLang: LatLng, zoom: Float = 10f) {
        mapa.moveCamera(
            CameraUpdateFactory.newLatLngZoom(latLang, zoom)
        )
    }

    fun anadirMarcador(latLang:LatLng, title:String): Marker {
        return mapa.addMarker(
            MarkerOptions()
                .position(latLang)
                .title(title)
        )!!
    }

    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.cl_google_maps),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }
}