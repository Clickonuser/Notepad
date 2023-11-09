package com.example.notepad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.notepad.room.AppDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var stubContainer: LinearLayout
    private lateinit var fab: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomAdapter
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stubContainer = findViewById(R.id.main_no_items_container)
        fab = findViewById(R.id.main_fab)

        fab.setOnClickListener {
            val dialog = CustomDialog(this)
            dialog.show()
        }

        recyclerView = findViewById(R.id.main_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ToDoItem>()

        if(data.isEmpty()) {
            stubContainer.visibility = View.VISIBLE
            recyclerView.visibility = View.INVISIBLE
        } else {
            stubContainer.visibility = View.INVISIBLE
            recyclerView.visibility = View.VISIBLE
        }

        adapter = CustomAdapter(data)
        recyclerView.adapter = adapter

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "database-name"
        )
            .allowMainThreadQueries()
            .build()

    }

    fun insertItem(item: ToDoItem) {
        db.todoDao().insertItem(item)
        stubContainer.visibility = View.INVISIBLE
        recyclerView.visibility = View.VISIBLE
    }
}