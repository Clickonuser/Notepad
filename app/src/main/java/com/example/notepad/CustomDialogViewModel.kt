package com.example.notepad

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notepad.repositories.PrefsRepository
import com.example.notepad.repositories.PrefsRepositoryImp

class CustomDialogViewModel(app: Application) : AndroidViewModel(app) {

    private val prefsRepository: PrefsRepository = PrefsRepositoryImp(app)

    private val dataFromPrefs: MutableLiveData<ToDoItem> = MutableLiveData()
    val dataFromPrefsResult: LiveData<ToDoItem> = dataFromPrefs

    private val idFromPrefs: MutableLiveData<Int> = MutableLiveData()
    val idFromPrefsResult: LiveData<Int> = idFromPrefs

    /**
     * gets value from sharedPreferences for TodoItem
     */
    fun getTitleAndDescriptionFromPrefs(titleKey: String, descriptionKey: String) {
        val title = prefsRepository.getStringFromPrefs(titleKey)
        val description = prefsRepository.getStringFromPrefs(descriptionKey)
        dataFromPrefs.postValue(ToDoItem(0, title, description))
    }

    /**
     * Save String in shared preferences repository
     * @param key - provide prefs information to save data
     * @param value - provide data that need to be saved in prefs
     */
    fun saveStringInPrefs(key: String, value: String) {
        prefsRepository.saveStringInPrefs(key, value)
    }

    /**
     * Save Int value in shared preferences repository
     */
    fun saveIntInPrefs(key: String, value: Int) {
        prefsRepository.saveIntInPrefs(key, value)
    }

    /**
     * gets id from sharedPreferences and posts to mutableLiveData
     */
    fun getIdFromPrefs(key: String) {
        val result = prefsRepository.getIntFromPrefs(key)
        idFromPrefs.postValue(result)
    }

}