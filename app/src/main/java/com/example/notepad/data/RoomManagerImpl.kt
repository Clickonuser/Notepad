package com.example.notepad.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.notepad.IRoomManager
import com.example.notepad.ToDoItem
import com.example.notepad.room.AppDatabase

class RoomManagerImpl(private val context: Context) : IRoomManager {

    private var db: AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    override fun getAllItems(): LiveData<List<ToDoItem>> {
        return db.todoDao().getAllItems()
    }

    override fun insertItem(item: ToDoItem) {
        db.todoDao().insertItem(item)
    }

    override fun updateItem(item: ToDoItem) {
        db.todoDao().updateItem(item)
    }

    override fun deleteItem(item: ToDoItem) {
        db.todoDao().deleteItem(item)
    }

}