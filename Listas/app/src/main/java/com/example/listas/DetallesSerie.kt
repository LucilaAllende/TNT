package com.example.listas

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetallesSerie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_serie)

        val extras: Bundle? = intent?.extras
        val txtDetalle: TextView? = findViewById(R.id.txtDetalle)
        val datos = extras?.getString("title")

        if (extras != null) {
            txtDetalle?.text= datos
        }
    }
}
