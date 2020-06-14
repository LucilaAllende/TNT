package com.example.listas

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.content_item.view.*


//clase adaptador para recibir los datos
class AdapterSerie (var list: ArrayList<Serie>): RecyclerView.Adapter<AdapterSerie.ViewHolder>(){
    //clase para manejar nuestra vista
    class ViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener { //el view que vamos agregar dentro de este es el view que recibimos en la clase viewHolder

        private val btnVer: Button = view.findViewById(R.id.btnVer)
        private val context: Context = view.context

        //recibimos lo datos que se agregan dentro de nuestra vista
        fun bindItems (data: Serie){

            //variables para nuestras vistas
            val title: TextView = itemView.findViewById(R.id.txtTitle)
            val tempo: TextView = itemView.findViewById(R.id.txtTemporadas)
            val thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)

            title.text = data.name
            tempo.text = data.temporadas.toString()

            Glide.with(itemView.context).load(data.thumbnail).into(thumbnail)

            itemView.setOnClickListener{
                Toast.makeText(itemView.context, "Ver ${data.name}", Toast.LENGTH_SHORT).show()
            }

        }

        fun escuchame(){
            btnVer.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.btnVer -> {
                    val intent = Intent(context, DetallesSerie::class.java)
                    intent.putExtra("title", itemView.txtTitle.text);
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.content_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])

        //set eventos
        holder.escuchame()
    }
}
