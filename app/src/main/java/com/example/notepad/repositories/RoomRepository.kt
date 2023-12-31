package com.example.notepad.repositories

import com.example.notepad.ToDoItem

interface RoomRepository {

    /**
     * Return data from data base
     */
    fun getAllItems(): List<ToDoItem>

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