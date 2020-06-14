package com.example.appnotificacion

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
//import androidx.databinding.DataBindingUtil
//import com.example.appnotificacion.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    //lateinit var binding: ActivityMainBinding

    private var boton: Button? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(R.layout.activity_main);
        this.boton = findViewById(R.id.boton_lanzar_notificacion);

        crearCanal(
                getString(R.string.pomodoro_notification_channel_id),
                getString(R.string.pomodoro_notification_channel_name)
        )

        boton?.setOnClickListener {
            notificar("Notificacion de Pomodoro!")
        }

    }

    private fun crearCanal(idCanal: String, nombreCanal: String) {

        // Crear canal de notificacion para versiones superiores a API 26.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val canalNotificacion = NotificationChannel(
                    idCanal,
                    nombreCanal,
                    NotificationManager.IMPORTANCE_HIGH
            )
                    .apply {
                        setShowBadge(false)
                        enableLights(true)
                        lightColor = Color.RED
                        enableVibration(true)
                        description = getString(R.string.pomodoro_notification_channel_description)
                    }

            // Registrar el canal
            val notificationManager = getSystemService(
                    NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(canalNotificacion)

        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun notificar(mensaje: String){
        val notificationManager = getSystemService(
                NotificationManager::class.java
        )
        notificationManager.sendNotification(
                mensaje,
                this)
    }

}
