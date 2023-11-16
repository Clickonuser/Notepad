package com.example.notepad

import androidx.lifecycle.LiveData

interface IRoomManager {

    fun getAllItems(): LiveData<List<ToDoItem>>

    fun insertItem(item: ToDoItem)

    fun updateItem(item: ToDoItem)

    fun deleteItem(item: ToDoItem)

}