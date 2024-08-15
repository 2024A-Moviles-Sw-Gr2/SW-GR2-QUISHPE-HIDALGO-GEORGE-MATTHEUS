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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class FormSeleccionarUbiPedido : AppCompatActivity() {

    private lateinit var mapa: GoogleMap
    var permisos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_seleccionar_ubi_pedido)

        var accion = intent.getStringExtra("accion")

        solicitarPermisos()
        iniciarLogicaMapa()

        val botonAgregarUbicacion = findViewById<Button>(R.id.btn_guardar_ubi_pedido)
        botonAgregarUbicacion.setOnClickListener {
            if(accion != "actualizar"){
                val intentCrearP = Intent(this, FormCrearPedido::class.java)
                intentCrearP.putExtra("nombreForm", "FormSeleccionarUbiPedido")
                intentCrearP.putExtra("idCliente", intent.getStringExtra("idCliente"))
                intentCrearP.putExtra("nombreCliente", intent.getStringExtra("nombreCliente"))
                intentCrearP.putExtra("fechaPedido", intent.getStringExtra("fechaPedido"))
                intentCrearP.putExtra("montoPedido", intent.getStringExtra("montoPedido"))
                intentCrearP.putExtra("descripcionPedido", intent.getStringExtra("descripcionPedido"))
                intentCrearP.putExtra("pagadoPedido", intent.getStringExtra("pagadoPedido"))
                intentCrearP.putExtra("latitudPedido", findViewById<android.widget.TextView>(R.id.tv_latitud).text.toString())
                intentCrearP.putExtra("longitudPedido", findViewById<android.widget.TextView>(R.id.tv_longitud).text.toString())
                startActivity(intentCrearP)
            } else {
                val intentActualizarP = Intent(this, FormActualizarPedido::class.java)
                intentActualizarP.putExtra("nombreForm", "FormSeleccionarUbiPedido")
                intentActualizarP.putExtra("pedidoID", intent.getStringExtra("pedidoID"))
                intentActualizarP.putExtra("idCliente", intent.getStringExtra("idCliente"))
                intentActualizarP.putExtra("nombreCliente", intent.getStringExtra("nombreCliente"))
                intentActualizarP.putExtra("fechaPedido", intent.getStringExtra("fechaPedido"))
                intentActualizarP.putExtra("montoPedido", intent.getStringExtra("montoPedido"))
                intentActualizarP.putExtra("descripcionPedido", intent.getStringExtra("descripcionPedido"))
                intentActualizarP.putExtra("pagadoPedido", intent.getStringExtra("pagadoPedido"))
                intentActualizarP.putExtra("latitudPedido", findViewById<android.widget.TextView>(R.id.tv_latitud).text.toString())
                intentActualizarP.putExtra("longitudPedido", findViewById<android.widget.TextView>(R.id.tv_longitud).text.toString())
                startActivity(intentActualizarP)
            }
        }

        val botonCancelar = findViewById<Button>(R.id.btn_cancel_agregar_ubi_pedido)
        botonCancelar.setOnClickListener {
            if(accion != "actualizar"){
                val intentCrearP = Intent(this, FormCrearPedido::class.java)
                intentCrearP.putExtra("nombreForm", "FormSeleccionarUbiPedido")
                intentCrearP.putExtra("pedidoID", intent.getStringExtra("pedidoID"))
                intentCrearP.putExtra("idCliente", intent.getStringExtra("idCliente"))
                intentCrearP.putExtra("nombreCliente", intent.getStringExtra("nombreCliente"))
                intentCrearP.putExtra("fechaPedido", intent.getStringExtra("fechaPedido"))
                intentCrearP.putExtra("montoPedido", intent.getStringExtra("montoPedido"))
                intentCrearP.putExtra("descripcionPedido", intent.getStringExtra("descripcionPedido"))
                intentCrearP.putExtra("pagadoPedido", intent.getStringExtra("pagadoPedido"))
                intentCrearP.putExtra("latitudPedido", intent.getStringExtra("latitudPedido"))
                intentCrearP.putExtra("longitudPedido", intent.getStringExtra("longitudPedido"))
                startActivity(intentCrearP)
            } else {
                val intentActualizarP = Intent(this, FormActualizarPedido::class.java)
                intentActualizarP.putExtra("nombreForm", "FormSeleccionarUbiPedido")
                intentActualizarP.putExtra("pedidoID", intent.getStringExtra("pedidoID"))
                intentActualizarP.putExtra("idCliente", intent.getStringExtra("idCliente"))
                intentActualizarP.putExtra("nombreCliente", intent.getStringExtra("nombreCliente"))
                intentActualizarP.putExtra("fechaPedido", intent.getStringExtra("fechaPedido"))
                intentActualizarP.putExtra("montoPedido", intent.getStringExtra("montoPedido"))
                intentActualizarP.putExtra("descripcionPedido", intent.getStringExtra("descripcionPedido"))
                intentActualizarP.putExtra("pagadoPedido", intent.getStringExtra("pagadoPedido"))
                intentActualizarP.putExtra("latitudPedido", intent.getStringExtra("latitudPedido"))
                intentActualizarP.putExtra("longitudPedido", intent.getStringExtra("longitudPedido"))
                startActivity(intentActualizarP)
            }
        }

        val botonVolver = findViewById<ImageButton>(R.id.btn_regresar_selec_ubi_form_anterior)
        botonVolver.setOnClickListener {
            if(accion != "actualizar"){
                val intentCrearP = Intent(this, FormCrearPedido::class.java)
                intentCrearP.putExtra("nombreForm", "FormSeleccionarUbiPedido")
                intentCrearP.putExtra("pedidoID", intent.getStringExtra("pedidoID"))
                intentCrearP.putExtra("idCliente", intent.getStringExtra("idCliente"))
                intentCrearP.putExtra("nombreCliente", intent.getStringExtra("nombreCliente"))
                intentCrearP.putExtra("fechaPedido", intent.getStringExtra("fechaPedido"))
                intentCrearP.putExtra("montoPedido", intent.getStringExtra("montoPedido"))
                intentCrearP.putExtra("descripcionPedido", intent.getStringExtra("descripcionPedido"))
                intentCrearP.putExtra("pagadoPedido", intent.getStringExtra("pagadoPedido"))
                intentCrearP.putExtra("latitudPedido", intent.getStringExtra("latitudPedido"))
                intentCrearP.putExtra("longitudPedido", intent.getStringExtra("longitudPedido"))
                startActivity(intentCrearP)
            } else {
                val intentActualizarP = Intent(this, FormActualizarPedido::class.java)
                intentActualizarP.putExtra("nombreForm", "FormSeleccionarUbiPedido")
                intentActualizarP.putExtra("pedidoID", intent.getStringExtra("pedidoID"))
                intentActualizarP.putExtra("idCliente", intent.getStringExtra("idCliente"))
                intentActualizarP.putExtra("nombreCliente", intent.getStringExtra("nombreCliente"))
                intentActualizarP.putExtra("fechaPedido", intent.getStringExtra("fechaPedido"))
                intentActualizarP.putExtra("montoPedido", intent.getStringExtra("montoPedido"))
                intentActualizarP.putExtra("descripcionPedido", intent.getStringExtra("descripcionPedido"))
                intentActualizarP.putExtra("pagadoPedido", intent.getStringExtra("pagadoPedido"))
                intentActualizarP.putExtra("latitudPedido", intent.getStringExtra("latitudPedido"))
                intentActualizarP.putExtra("longitudPedido", intent.getStringExtra("longitudPedido"))
                startActivity(intentActualizarP)
            }
        }
    }

    fun solicitarPermisos() {
        val contexto = this.applicationContext
        val nombrePermisoFine = android.Manifest.permission.ACCESS_FINE_LOCATION
        val nombrePermisoCoarse = android.Manifest.permission.ACCESS_COARSE_LOCATION
        val permisoFine = ContextCompat.checkSelfPermission(contexto, nombrePermisoFine)
        val permisoCoarse = ContextCompat.checkSelfPermission(contexto, nombrePermisoCoarse)
        val tienePermisos = permisoFine == android.content.pm.PackageManager.PERMISSION_GRANTED &&
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
            .findFragmentById(R.id.map_pedido_agregar_ubicacion) as com.google.android.gms.maps.SupportMapFragment
        fragmentoMapa.getMapAsync {
                googleMap ->
            with(googleMap){
                mapa = googleMap
                establecerConfiguracionMapa()
                this.setOnMapClickListener { latLng ->
                    seleccionarUbicacion(latLng)
                }
                val ubiQuito = LatLng(-0.22, -78.5125)
                moverCamaraConZoom(ubiQuito)
            }
        }
    }

    fun seleccionarUbicacion (LatLng: LatLng){
        val tvLatitud = findViewById<android.widget.TextView>(R.id.tv_latitud)
        val tvLongitud = findViewById<android.widget.TextView>(R.id.tv_longitud)
        tvLatitud.text = LatLng.latitude.toString()
        tvLongitud.text = LatLng.longitude.toString()
    }

    fun moverCamaraConZoom(latLang: LatLng, zoom: Float = 10f) {
        mapa.moveCamera(
            CameraUpdateFactory.newLatLngZoom(latLang, zoom)
        )
    }

}