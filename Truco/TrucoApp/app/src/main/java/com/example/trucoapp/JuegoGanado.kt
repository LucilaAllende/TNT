package com.example.trucoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trucoapp.databinding.ActivityJuegoGanadoBinding
import kotlinx.android.synthetic.main.activity_juego_ganado.view.*

class JuegoGanado : AppCompatActivity() {

    private lateinit var vista: ActivityJuegoGanadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vista = ActivityJuegoGanadoBinding.inflate(layoutInflater)
        val view = vista.root
        setContentView(view)

        vista.botonJugaragain.setOnClickListener { jugar_again() }

        val intent1: Intent = intent
        var partidasEllos = intent1.getIntegerArrayListExtra("jugadasEllos")
        var partidaNosotros = intent1.getIntegerArrayListExtra("jugadasNosotros")

        vista.ellos1.text= partidasEllos.first().toString()
        vista.nosotros1.text=partidaNosotros.first().toString()

        vista.ellos2.text = partidasEllos.last().toString()
        vista.nosotros2.text = partidaNosotros.last().toString()


    }

    private fun jugar_again(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


}
