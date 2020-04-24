package com.example.listas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView:RecyclerView = findViewById(R.id.recyclear)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val series= ArrayList<Serie>()

        series.add(Serie("Atypical", 3, R.drawable.serie1))
        series.add(Serie("You", 2, R.drawable.serie2))
        series.add(Serie("Stranger Things", 3, R.drawable.serie3))
        series.add(Serie("Riverdale", 3, R.drawable.serie4))

        val adapter = AdapterSerie(series)
        recyclerView.adapter=adapter
    }

}

