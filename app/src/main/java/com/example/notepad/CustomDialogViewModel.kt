package com.example.notepad

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notepad.repositories.PrefsRepository
import com.example.notepad.repositories.PrefsRepositoryImp

class CustomDialogViewModel(app: Application) : AndroidViewModel(app) {

    private val prefManager: PrefsRepository = PrefsRepositoryImp(app)

    private val dataFromPrefs: MutableLiveData<ToDoItem> = MutableLiveData()
    val dataFromPrefsResult: LiveData<ToDoItem> = dataFromPrefs

    /**
     * Provides preferences values for TodoItem
     */
    fun getTitleAndDescriptionFromPrefs(titleKey: String, descriptionKey: String) {
        val title = prefManager.getStringFromPrefs(titleKey)
        val description = prefManager.getStringFromPrefs(descriptionKey)
        dataFromPrefs.postValue(ToDoItem(0, title, description))
    }

    /**
     * Save data in shared preferences manager
     * @param key - provide prefs information to save data
     * @param value - provide data that need to be saved in prefs
     */
    fun saveStringInPrefs(key: String, value: String) {
        prefManager.saveStringInPrefs(key, value)
    }

}