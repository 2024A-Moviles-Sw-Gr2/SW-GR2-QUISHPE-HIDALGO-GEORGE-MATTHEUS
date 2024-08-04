package com.example.a2024swgr2qhgm

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.material.snackbar.Snackbar

class GGoogleMapsActivity : AppCompatActivity() {
    private lateinit var mapa: GoogleMap
    var permisos = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ggoogle_maps)
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
            .findFragmentById(R.id.map) as com.google.android.gms.maps.SupportMapFragment
        fragmentoMapa.getMapAsync {
                googleMap ->
            with(googleMap){
                mapa = googleMap
                establecerConfiguracionMapa()
                moverQuincentro()
                anadirPolilinea()
                anadirPoligono()
            }
        }
    }

    fun moverQuincentro() {
        val zoom = 17f
        val quicentro = LatLng(
            -0.17589782309079616,
            -78.47924184856251
        )

        val titulo = "Quicentro"
        val markQuicentro = anadirMarcador(quicentro, titulo)
        markQuicentro.tag = titulo
        moverCamaraConZoom(quicentro, zoom)

    }

    fun anadirPolilinea(){
        with(mapa){
            val polilineaUno = addPolyline(
                PolylineOptions()
                    .clickable(true)
                    .add(
                        LatLng(-0.1758614401269219, -78.48571121689449),
                        LatLng(-0.17843634842358283, -78.48244965071446),
                        LatLng(-0.17843634842358283, -78.47927391522337)
                    )
            )
            polilineaUno.tag = "Polilinea-uno"
        }
    }

    fun anadirPoligono(){
        with(mapa){
            val poligonoUno = mapa.addPolygon(
                PolygonOptions().clickable(true)
                    .add(

                        LatLng(-0.172342398151301, -78.48596870896792),
                        LatLng(-0.17508896750495734, -78.48124802107579),
                        LatLng(-0.17345819199934728, -78.47584068767206)
                    )
            )
            poligonoUno.tag = "Poligono-uno"
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