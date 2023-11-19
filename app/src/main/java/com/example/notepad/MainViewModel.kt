package com.example.notepad

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notepad.data.RoomManagerImpl

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val roomManager: IRoomManager = RoomManagerImpl(app)

    private var todoItemList: LiveData<List<ToDoItem>> = roomManager.getAllItems()
    val todoItemListResult: LiveData<List<ToDoItem>> = todoItemList

    /**
     * Provides all data from room
     */
    fun getAllItems() {
        todoItemList = roomManager.getAllItems()
    }

    /**
     * Insert new item in room data base
     * @param item - provide item that need to be insert in room data base
     */
    fun insertItem(item: ToDoItem) {
        roomManager.insertItem(item)
    }

    /**
     * Updated existing item in room data base
     * @param item - provide item that need to be updated in room data base
     */
    fun updateItem(item: ToDoItem) {
        roomManager.updateItem(item)
    }

    /**
     * Delete existing item from room data base
     * @param item - provide item that need to be deleted from room data base
     */
    fun deleteItem(item: ToDoItem) {
        roomManager.deleteItem(item)
    }

}