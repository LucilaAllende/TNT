package com.example.geofencingcopia

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

// TODO #3 Crear un MapsActivity

open class MapsActivity : AppCompatActivity(), OnMapReadyCallback, OnMapLongClickListener {

    private lateinit var mMap: GoogleMap

    // TODO #7 Declarar variable para cliente Geofence
    private lateinit var geofencingClient: GeofencingClient

    // TODO #9.1 Declaramos bandera
    private var FINE_LOCATION_ACCESS_REQUEST_CODE: Int = 10001

    // TODO #13.4 Contaste para el tamaño del radio del circulo
    private val GEOFENCE_RADIUS = 200f

    // TODO #17 Declarar variable para Geofence Helper
    private lateinit var geofenceHelper: GeofenceHelper

    // TODO #19.1 Declarar variable para Geofence Helper
    private val GEOFENCE_ID = "SOME_GEOFENCE_ID"

    // TODO #19.3
    private val TAG = "MapsActivity"


    private val BACKGROUND_LOCATION_ACCESS_REQUEST_CODE = 10002

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        // TODO #8 Inicializamos cliente Geofence
        geofencingClient = LocationServices.getGeofencingClient(this)
        // TODO #18 Inicializamos Geofence Helper
        geofenceHelper = GeofenceHelper(this)
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
        mMap = googleMap

        /* Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/


        // TODO #11 Add a marker in Torre Eiffel, move the camera and zoom
        //val eiffel = LatLng(48.8589, 2.29365)
        //val eiffel = LatLng(-43.240669, -65.296826) //Casa

        val eiffel = LatLng(-43.2435, -65.2995)

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eiffel, 16f))


        // TODO #9.2 Llamamos a la funcion
        enableUserLocation()

        // TODO #13 Google maps va ser un escuchador y agregamos interfaz (?)arriba
        //mMap.setOnMapLongClickListener(this)

        onMapLongClick(eiffel)

    }

    // TODO #9 Creamos funcion para habilitar la ubicacion del usuario
    private fun enableUserLocation() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
        } else {
            //Solicitar permiso
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                //Necesitamos mostrarle al usuario un cuadro de diálogo para mostrar por qué se necesita el permiso y luego pedir el permiso ...
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    FINE_LOCATION_ACCESS_REQUEST_CODE
                )
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    FINE_LOCATION_ACCESS_REQUEST_CODE
                )
            }
        }
    }

    // TODO #10 Redefinimos que hacer con elr esultado del permiso (creo)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (requestCode == FINE_LOCATION_ACCESS_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //We have the permission
                mMap.isMyLocationEnabled = true
            } else {
                //We do not have the permission..
            }
        }

        if (requestCode == BACKGROUND_LOCATION_ACCESS_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //We have the permission
                Toast.makeText(this, "You can add geofences...", Toast.LENGTH_SHORT).show()
            } else {
                //We do not have the permission..
                Toast.makeText(
                    this,
                    "Background location access is neccessary for geofences to trigger...",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    // TODO #13.1 Implementar miembros de la interfaz
    // TODO #21 Agregamos lo de background y la nueva funcion en reeemplazo
    override fun onMapLongClick(latLng: LatLng?) {
        if (Build.VERSION.SDK_INT >= 29) {
            //We need background permission
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                handleMapLongClick(latLng)
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION
                    )
                ) {
                    //We show a dialog and ask for permission
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION),
                        BACKGROUND_LOCATION_ACCESS_REQUEST_CODE
                    )
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION),
                        BACKGROUND_LOCATION_ACCESS_REQUEST_CODE
                    )
                }
            }
        } else {
            handleMapLongClick(latLng)
        }
    }

    // TODO #21.1 Reemplazo de lo que era onMapLongClick para modularizar
    private fun handleMapLongClick(latLng: LatLng?) {
        mMap.clear()
        if (latLng != null) {
            addMarker(latLng)
        }
        if (latLng != null) {
            addCircle(latLng, GEOFENCE_RADIUS)
        }
        // TODO #19.4
        if (latLng != null) {
            addGeofence(latLng, GEOFENCE_RADIUS)
        }
    }

    // TODO #13.2 Funcion para definir propiedades del marcador
    private fun addMarker(latLng: LatLng) {
        val markerOptions = MarkerOptions().position(latLng)
        mMap.addMarker(markerOptions)
    }

    // TODO #13.3 Funcion para definir propiedades del circulo
    private fun addCircle(latLng: LatLng, radius: Float) {
        val circleOptions = CircleOptions()
        circleOptions.center(latLng)
        circleOptions.radius(radius.toDouble())
        circleOptions.strokeColor(Color.argb(255, 255, 0, 0))
        circleOptions.fillColor(Color.argb(64, 255, 0, 0))
        circleOptions.strokeWidth(4f)
        mMap.addCircle(circleOptions)
    }

    // TODO #19 Funcion para definir propiedades del geofence
    private fun addGeofence(latLng: LatLng, radius: Float) {
        val geofence = geofenceHelper.getGeofence(
            GEOFENCE_ID,
            latLng,
            radius,
            Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_DWELL or Geofence.GEOFENCE_TRANSITION_EXIT
        )
        val geofencingRequest = geofenceHelper.getGeofencingRequest(geofence!!)
        val pendingIntent = geofenceHelper.getPendingIntent()
        geofencingClient.addGeofences(geofencingRequest, pendingIntent)
            .addOnSuccessListener { Log.d(TAG, "onSuccess: Geofence Added...") }
            .addOnFailureListener { e ->
                val errorMessage = geofenceHelper.getErrorString(e)
                Log.d(TAG, "onFailure: $errorMessage")
            }
    }

}
