package com.example.notepad

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notepad.repositories.PrefsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CustomDialogViewModel @Inject constructor(
    private val prefsRepository: PrefsRepository
) : ViewModel() {

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