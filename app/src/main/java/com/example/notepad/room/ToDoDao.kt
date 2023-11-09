package com.example.notepad.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notepad.ToDoItem

    @Dao
    interface ToDoDao {

            @Query("SELECT * FROM toDoItem")
            fun getAllItems(): LiveData<List<ToDoItem>>

            @Insert
            fun insertItem(toDoItem: ToDoItem)

            @Delete
            fun deleteItem(toDoItem: ToDoItem)

            @Update
            fun updateItem(toDoItem: ToDoItem)
    }

