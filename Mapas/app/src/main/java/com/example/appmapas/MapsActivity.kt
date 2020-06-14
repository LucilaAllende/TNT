package com.example.appmapas

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import org.json.JSONArray
import org.json.JSONException
import java.io.InputStream
import java.util.*
import com.google.maps.android.heatmaps.HeatmapTileProvider

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private val SOLICITUD_PERMISO_UBICACION = 1
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        agregarMarcador1(map)
        agregarMarcador2(map)
        agregarMarcador3(map)
        activarUbicacion(map)
        addHeatMap(map)
    }

    private fun agregarMarcador3(map: GoogleMap) {
        val centroAstronomico = LatLng(-43.245513,-65.2981207)
        val nivelZoom = 15f

        this.map.addMarker(
            MarkerOptions()
                .position(centroAstronomico)
                .title("Marcador en centro Astronomico")
        )
        this.map.moveCamera(
            CameraUpdateFactory.newLatLngZoom(centroAstronomico, nivelZoom)
        )
    }

    private fun agregarMarcador2(map: GoogleMap) {
        val plazaIndepencia = LatLng(-43.253749847, -65.30963897705078)
        val nivelZoom = 15f

        this.map.addMarker(
                MarkerOptions()
                        .position(plazaIndepencia)
                        .title("Marcador en plaza Indepencia")
        )
        this.map.moveCamera(
                CameraUpdateFactory.newLatLngZoom(plazaIndepencia, nivelZoom)
        )
    }

    private fun agregarMarcador1(map: GoogleMap) {
        val shoppingTw = LatLng(-43.248951, -65.3050537)
        val nivelZoom = 15f

        this.map.addMarker(
                MarkerOptions()
                        .position(shoppingTw)
                        .title("Marcador en Shopping Tw")
        )
        this.map.moveCamera(
                CameraUpdateFactory.newLatLngZoom(shoppingTw, nivelZoom)
        )
    }

    private fun seOtorgoPermiso() : Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun activarUbicacion(map: GoogleMap) {
        if (seOtorgoPermiso()) {
            this.map.isMyLocationEnabled = true
        }
        else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                SOLICITUD_PERMISO_UBICACION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == SOLICITUD_PERMISO_UBICACION) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                activarUbicacion(map)
            }
        }
    }

    private fun addHeatMap(map: GoogleMap) {
        var list: List<LatLng?>? = null

        // Get the data: latitude/longitude positions of police stations.
        try {
            list = readItems(R.raw.police_stations)
        } catch (e: JSONException) {
            Toast.makeText(this, "Problem reading list of locations.", Toast.LENGTH_LONG).show()
        }

        // Create a heat map tile provider, passing it the latlngs of the police stations.
        var mProvider = HeatmapTileProvider.Builder()
            .data(list)
            .build()
        // Add a tile overlay to the map, using the heat map tile provider.
        var mOverlay = this.map.addTileOverlay(TileOverlayOptions().tileProvider(mProvider))
    }

    @Throws(JSONException::class)
    private fun readItems(resource: Int): ArrayList<LatLng?>? {
        val list = ArrayList<LatLng?>()
        val inputStream: InputStream = resources.openRawResource(resource)
        val json: String = Scanner(inputStream).useDelimiter("\\A").next()
        val array = JSONArray(json)
        for (i in 0 until array.length()) {
            val `object` = array.getJSONObject(i)
            val lat = `object`.getDouble("lat")
            val lng = `object`.getDouble("lng")
            list.add(LatLng(lat, lng))
        }
        return list
    }

}
