package com.example.notepad

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notepad.repositories.RoomRepository
import com.example.notepad.repositories.RoomRepositoryImpl

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val roomRepository: RoomRepository = RoomRepositoryImpl(app)

    private var todoItemList: LiveData<List<ToDoItem>> = roomRepository.getAllItems()
    val todoItemListResult: LiveData<List<ToDoItem>> = todoItemList

    /**
     * Provides all data from room
     */
    fun getAllItems() {
        todoItemList = roomRepository.getAllItems()
    }

    /**
     * Insert new item in room data base
     * @param item - provide item that need to be insert in room data base
     */
    fun insertItem(item: ToDoItem) {
        roomRepository.insertItem(item)
    }

    /**
     * Updated existing item in room data base
     * @param item - provide item that need to be updated in room data base
     */
    fun updateItem(item: ToDoItem) {
        roomRepository.updateItem(item)
    }

    /**
     * Delete existing item from room data base
     * @param item - provide item that need to be deleted from room data base
     */
    fun deleteItem(item: ToDoItem) {
        roomRepository.deleteItem(item)
    }

}