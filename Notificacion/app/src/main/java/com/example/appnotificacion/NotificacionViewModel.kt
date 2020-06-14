package com.example.appnotificacion

import android.app.Application
import android.app.NotificationManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel


class NotificacionViewModel(app: Application) : AndroidViewModel(app) {

    var isAlarmOn = true

    private fun notificar(){
        val notificationManager =
            ContextCompat.getSystemService(
                getApplication(),
                NotificationManager::class.java
            ) as NotificationManager
        notificationManager.sendNotification(
            "mensaje",
            getApplication())
    }
}