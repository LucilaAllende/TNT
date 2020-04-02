package com.example.trucoapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.trucoapp.databinding.TableroJuegoBinding

class TableroJuego : AppCompatActivity() {

    private lateinit var vista: TableroJuegoBinding
    private var puntajeGeneralEllos: Int = 0
    private var puntajeGeneralNosotros: Int = 0
    var puntajeNosotros: Int = 0
    var puntajeEllos:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vista = TableroJuegoBinding.inflate(layoutInflater)
        val view = vista.root
        setContentView(view)

        vista.mas1.setOnClickListener { ellos() }
        vista.mas2.setOnClickListener { nosotros() }

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

    }


}
