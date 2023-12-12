package com.example.notepad.repositories

import android.content.Context
import androidx.room.Room
import com.example.notepad.Constants.DATABASE_NAME
import com.example.notepad.ToDoItem
import com.example.notepad.room.AppDatabase

/**
 * use to manage work with room data base
 */
class RoomRepositoryImpl(private val context: Context) : RoomRepository {

    private var db: AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, DATABASE_NAME
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    override fun getAllItems(): List<ToDoItem> {
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