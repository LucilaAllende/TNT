package com.example.listas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView:RecyclerView = findViewById(R.id.recyclear)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val albums= ArrayList<Album>()

        albums.add(Album("Ariana Grande", 23, R.drawable.serie1))
        albums.add(Album("Camila Cabello", 12, R.drawable.serie2))
        albums.add(Album("Eminem", 11, R.drawable.serie3))
        albums.add(Album("Beyonce", 19, R.drawable.serie4))

        val adapter = AdapterAlbun(albums)
        recyclerView.adapter=adapter
    }

}

