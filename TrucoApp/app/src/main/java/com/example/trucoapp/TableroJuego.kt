package com.example.trucoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.trucoapp.databinding.TableroJuegoBinding

class TableroJuego : AppCompatActivity() {

    private lateinit var vista: TableroJuegoBinding
    private var puntajeGeneralEllos = 0
    private var puntajeGeneralNosotros: Int = 0
    private var puntajeEllos:Int = 0
    private var puntajeNosotros: Int = 0
    private var buenasEllos: Int = 0
    private var buenasNosotros:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vista = TableroJuegoBinding.inflate(layoutInflater)
        val view = vista.root
        setContentView(view)

        vista.mas1.setOnClickListener { ellos() }
        vista.mas2.setOnClickListener { nosotros() }
    }

    private fun limpiar_tablero_ellos() {

        vista.image11.setImageResource(R.drawable.ic_tantos_vacio)
        vista.image12.setImageResource(R.drawable.ic_tantos_vacio)
        vista.image13.setImageResource(R.drawable.ic_tantos_vacio)
    }

    private fun limpiar_tablero_nosotros() {

        vista.image21.setImageResource(R.drawable.ic_tantos_vacio)
        vista.image22.setImageResource(R.drawable.ic_tantos_vacio)
        vista.image23.setImageResource(R.drawable.ic_tantos_vacio)
    }

    private fun nosotros() {

        puntajeGeneralNosotros += 1

        if(puntajeGeneralNosotros in 1..5){
            responder_click_nosotros(vista.image21)
        }
        if(puntajeGeneralNosotros in 5..10){
            responder_click_nosotros(vista.image22)
        }
        if(puntajeGeneralNosotros in 10..15){
            responder_click_nosotros(vista.image23)
        }
    }

    private fun responder_click_nosotros(parametro: ImageView) {

        puntajeNosotros = if ( puntajeNosotros == 5) 0 else puntajeNosotros + 1

        var recursoImg = when(puntajeNosotros){
            1 -> R.drawable.ic_tantos_1
            2 -> R.drawable.ic_tantos_2
            3 -> R.drawable.ic_tantos_3
            4 -> R.drawable.ic_tantos_4
            5 -> R.drawable.ic_tantos_5
            else -> R.drawable.ic_tantos_vacio

        }
        parametro.setImageResource(recursoImg)

        comprobar_tantos_nosotros()
        comprobar_buenas_nosotros()
    }

    private fun comprobar_buenas_nosotros() {
        if (buenasNosotros == 2) {
            vista.idnosotros.text = "Ganamos nosotros"
            vista.mas1.isEnabled = false
            vista.mas2.isEnabled = false
        }
    }

    private fun comprobar_tantos_nosotros() {
        if (puntajeGeneralNosotros==15) {
            buenasNosotros += 1
            vista.idnosotros.text = "Nosotros estamos en buenas"
            puntajeGeneralNosotros = 0
            puntajeNosotros = 0
            limpiar_tablero_nosotros()
        }
    }

    private fun ellos(){

        puntajeGeneralEllos += 1
        if(puntajeGeneralEllos in 1..5){
            responder_click_ellos(vista.image11)
        }
        if(puntajeGeneralEllos in 5..10){
            responder_click_ellos(vista.image12)
        }
        if(puntajeGeneralEllos in 10..15){
            responder_click_ellos(vista.image13)
        }
    }

    private fun responder_click_ellos(parametro: ImageView) {

        puntajeEllos = if ( puntajeEllos == 5) 0 else puntajeEllos + 1

        var recursoImg = when(puntajeEllos){
            1 -> R.drawable.ic_tantos_1
            2 -> R.drawable.ic_tantos_2
            3 -> R.drawable.ic_tantos_3
            4 -> R.drawable.ic_tantos_4
            5 -> R.drawable.ic_tantos_5
            else -> R.drawable.ic_tantos_vacio

        }
        parametro.setImageResource(recursoImg)

        comprobar_tantos_ellos()
        comprobar_buenas_ellos()
    }

    private fun comprobar_buenas_ellos() {
        if (buenasEllos == 2) {
            vista.idellos.text = "Ganaron ellos"
            vista.mas1.isEnabled = false
            vista.mas2.isEnabled = false
        }
    }

    private fun comprobar_tantos_ellos() {
        if (puntajeGeneralEllos==15) {
            buenasEllos += 1
            vista.idellos.text = "Ellos están en buenas"
            puntajeGeneralEllos = 0
            puntajeEllos = 0
            limpiar_tablero_ellos()
        }
    }
}
