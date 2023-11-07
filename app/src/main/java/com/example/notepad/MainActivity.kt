package com.example.notepad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val stubContainer = findViewById<LinearLayout>(R.id.main_no_items_container)

        val recyclerView = findViewById<RecyclerView>(R.id.main_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ToDoItem>()
        for (i in 1..20) {
            data.add(ToDoItem(i.toString(), i.toString()))
        }

        val adapter = CustomAdapter(data)
        recyclerView.adapter = adapter

        if(data.isEmpty()) {
            stubContainer.visibility = View.VISIBLE
            recyclerView.visibility = View.INVISIBLE
        } else {
            stubContainer.visibility = View.INVISIBLE
            recyclerView.visibility = View.VISIBLE
        }

    }
}