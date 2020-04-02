package com.example.trucoapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //Se llama cuando el usuario toca el botón Jugar
    fun empezarJuego(view: View) {
        val intent = Intent(this, TableroJuego::class.java)
        startActivity(intent)
    }
}
