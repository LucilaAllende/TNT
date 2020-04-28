package com.example.trucoapp

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.trucoapp.databinding.TableroJuegoBinding

class TableroJuego : AppCompatActivity() {

    private lateinit var vista: TableroJuegoBinding
    private var jugadasEllos = arrayListOf<Int>()
    private var jugadasNosotros = arrayListOf<Int>()
    private var puntajeGeneralEllos: Int = 0
    private var puntajeGeneralNosotros: Int = 0
    private var puntajeEllos: Int = 0
    private var puntajeNosotros: Int = 0
    private var indiceEllos:Int = 0
    private var indiceNosotros: Int = 0
    private var buenasEllos: Int = 0
    private var buenasNosotros:Int = 0

    private var iterador: Int = 0

    var v: String? = null
    var pos: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vista = TableroJuegoBinding.inflate(layoutInflater)
        val view = vista.root
        setContentView(view)

        vista.botonContinuar.visibility= View.INVISIBLE
        vista.mas1.setOnClickListener { ellos() }
        vista.mas2.setOnClickListener { nosotros() }
    }

    override fun onStop() {
        super.onStop()
        val values = ContentValues().apply {
            put("jugadasEllos", jugadasEllos)
            put("jugadaNosotros", jugadasNosotros)
        }
    }

    override fun onSaveInstanceState(guardarEstado: Bundle) {
        super.onSaveInstanceState(guardarEstado)
        guardarEstado.putIntegerArrayList("jugadasEllos",jugadasEllos)
        guardarEstado.putIntegerArrayList("jugadasNosotros", jugadasNosotros)
    }

    override fun onRestoreInstanceState(recEstado: Bundle) {
        super.onRestoreInstanceState(recEstado)
        jugadasEllos = recEstado.getIntegerArrayList("jugadasEllos") as ArrayList<Int>
        jugadasNosotros = recEstado.getIntegerArrayList("jugadasNosotros") as ArrayList<Int>
    }

    private fun juego_terminado(){

        val intent = Intent(this, JuegoGanado::class.java)
        intent.putIntegerArrayListExtra("jugadasEllos", jugadasEllos)
        intent.putIntegerArrayListExtra("jugadasNosotros", jugadasNosotros)

        jugadasEllos.add(puntajeGeneralEllos)
        jugadasNosotros.add(puntajeGeneralNosotros)

        intent.putIntegerArrayListExtra("jugadasEllos", jugadasEllos)
        intent.putIntegerArrayListExtra("jugadasNosotros", jugadasNosotros)

        startActivity(intent)
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

        puntajeNosotros += 1
        puntajeGeneralNosotros +=1

        if(puntajeNosotros in 1..5){
            responder_click_nosotros(vista.image21)
        }
        if(puntajeNosotros in 5..10){
            responder_click_nosotros(vista.image22)
        }
        if(puntajeNosotros in 10..15){
            responder_click_nosotros(vista.image23)
        }
    }

    private fun responder_click_nosotros(parametro: ImageView) {

        indiceNosotros = if ( indiceNosotros == 5) 0 else indiceNosotros + 1

        var recursoImg = when(indiceNosotros){
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

    private fun comprobar_tantos_nosotros() {
        if (puntajeNosotros==15) {
            buenasNosotros += 1
            vista.idnosotros.text = "Nosotros estamos en buenas"
            puntajeNosotros = 0
            indiceNosotros = 0
            limpiar_tablero_nosotros()
        }

    }

    private fun comprobar_buenas_nosotros() {
        if (buenasNosotros == 2) {
            vista.idnosotros.text = "Ganamos nosotros"
            vista.mas1.isEnabled = false
            vista.mas2.isEnabled = false
            vista.botonContinuar.visibility= View.VISIBLE
            iterador += 1
            //jugadasEllos.set(iterador,puntajeEllos)
            //jugadasNosotros.set(iterador,puntajeNosotros)
            vista.botonContinuar.setOnClickListener { juego_terminado() }
        }
    }

    private fun ellos(){
        puntajeGeneralEllos +=1
        puntajeEllos += 1
        if(puntajeEllos in 1..5){
            responder_click_ellos(vista.image11)
        }
        if(puntajeEllos in 5..10){
            responder_click_ellos(vista.image12)
        }
        if(puntajeEllos in 10..15){
            responder_click_ellos(vista.image13)
        }
    }

    private fun responder_click_ellos(parametro: ImageView) {

        indiceEllos = if ( indiceEllos == 5) 0 else indiceEllos + 1

        var recursoImg = when(indiceEllos){
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

    private fun comprobar_tantos_ellos() {
        if (puntajeEllos==15) {
            buenasEllos += 1
            vista.idellos.text = "Ellos están en buenas"
            puntajeEllos = 0
            indiceEllos = 0
            limpiar_tablero_ellos()
        }
    }

    private fun comprobar_buenas_ellos() {
        if (buenasEllos == 2) {
            vista.idellos.text = "Ganaron ellos"
            vista.mas1.isEnabled = false
            vista.mas2.isEnabled = false
            vista.botonContinuar.visibility= View.VISIBLE
            iterador += 1
            //jugadasEllos.set(iterador,puntajeEllos)
            //jugadasNosotros.set(iterador,puntajeNosotros)
            vista.botonContinuar.setOnClickListener { juego_terminado() }
        }
    }


}