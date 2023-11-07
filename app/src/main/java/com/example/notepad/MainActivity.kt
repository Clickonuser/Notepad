package com.example.notepad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.main_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ToDoItem>()
        for (i in 1..20) {
            data.add(ToDoItem(i.toString(), i.toString()))
        }

        val adapter = CustomAdapter(data)
        recyclerView.adapter = adapter

    }
}