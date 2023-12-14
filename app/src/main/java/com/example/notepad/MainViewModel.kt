package com.example.notepad

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notepad.repositories.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val roomRepository: RoomRepository
) : ViewModel() {

    private val todoItemList: MutableLiveData<List<ToDoItem>> = MutableLiveData()
    val todoItemListResult: LiveData<List<ToDoItem>> = todoItemList

    /**
     * Provides all data from room
     */
    fun getAllItems() {
        val result = roomRepository.getAllItems()
        todoItemList.postValue(result)
    }

    /**
     * Insert new item in room data base
     * @param item - provide item that need to be insert in room data base
     */
    fun insertItem(item: ToDoItem) {
        val currentList = todoItemList.value?.toMutableList() ?: mutableListOf()
        val indexToInsert = currentList.indexOfFirst { it.id > item.id }
        if (indexToInsert != -1) {
            currentList.add(indexToInsert, item)
        } else {
            currentList.add(item)
        }
        todoItemList.postValue(currentList)
        roomRepository.insertItem(item)
    }

    /**
     * Updated existing item in room data base
     * @param item - provide item that need to be updated in room data base
     */
    fun updateItem(item: ToDoItem) {
        val currentList = todoItemList.value?.toMutableList() ?: mutableListOf()
        val indexToUpdate = currentList.indexOfFirst { it.id == item.id }
        if (indexToUpdate != -1) {
            currentList[indexToUpdate] = item
        }
        todoItemList.postValue(currentList)
        roomRepository.updateItem(item)
    }

    /**
     * Delete existing item from room data base
     * @param item - provide item that need to be deleted from room data base
     */
    fun deleteItem(item: ToDoItem) {
        todoItemList.value.let {
            todoItemList.postValue(it?.minus(item))
        }
        roomRepository.deleteItem(item)
    }

}