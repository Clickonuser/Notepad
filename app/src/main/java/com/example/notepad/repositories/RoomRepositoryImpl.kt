package com.example.notepad.repositories

import com.example.notepad.ToDoItem
import com.example.notepad.room.ToDoDao
import javax.inject.Inject

/**
 * use to manage work with room data base
 */
class RoomRepositoryImpl @Inject constructor(
    private val toDoDao: ToDoDao
) : RoomRepository {

    override fun getAllItems(): List<ToDoItem> {
        return toDoDao.getAllItems()
    }

    override fun insertItem(item: ToDoItem) {
        toDoDao.insertItem(item)
    }

    override fun updateItem(item: ToDoItem) {
        toDoDao.updateItem(item)
    }

    override fun deleteItem(item: ToDoItem) {
        toDoDao.deleteItem(item)
    }

}