package com.example.notepad

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notepad.data.RoomManagerImpl

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val roomManager: IRoomManager = RoomManagerImpl(app)

    private var todoItemList: LiveData<List<ToDoItem>> = roomManager.getAllItems()
    val todoItemListResult: LiveData<List<ToDoItem>> = todoItemList

    fun getAllItems() {
        todoItemList = roomManager.getAllItems()
    }

    fun insertItem(item: ToDoItem) {
        roomManager.insertItem(item)
    }

    fun updateItem(item: ToDoItem) {
        roomManager.updateItem(item)
    }

    fun deleteItem(item: ToDoItem) {
        roomManager.deleteItem(item)
    }

}