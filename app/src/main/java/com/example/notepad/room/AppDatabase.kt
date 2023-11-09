package com.example.notepad.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notepad.ToDoItem

    @Database(entities = [ToDoItem::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun todoDao(): ToDoDao
    }


