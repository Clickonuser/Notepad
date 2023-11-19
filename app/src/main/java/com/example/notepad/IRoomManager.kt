package com.example.notepad

import androidx.lifecycle.LiveData

interface IRoomManager {

    /**
     * Return data from data base
     */
    fun getAllItems(): LiveData<List<ToDoItem>>

    /**
     * Adding element to data base
     */
    fun insertItem(item: ToDoItem)

    /**
     * updating element in data base
     */
    fun updateItem(item: ToDoItem)

    /**
     * deletion element from data base
     */
    fun deleteItem(item: ToDoItem)

}