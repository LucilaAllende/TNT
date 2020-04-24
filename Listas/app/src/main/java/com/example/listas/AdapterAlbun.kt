package com.example.listas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AdapterAlbun (var list: ArrayList<Album>): RecyclerView.Adapter<AdapterAlbun.ViewHolder>(){

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){ //manejar nuestra vista

        fun bindItems (data: Album){
            val title: TextView = itemView.findViewById(R.id.txtTitle)
            val count: TextView = itemView.findViewById(R.id.txtCount)
            val thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)

            title.text = data.name
            count.text = data.numOfSongs.toString()

            Glide.with(itemView.context).load(data.thumbnail).into(thumbnail)

            itemView.setOnClickListener{
                Toast.makeText(itemView.context, "Albun de ${data.name}", Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent?.context).inflate(R.layout.content_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AdapterAlbun.ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }
}