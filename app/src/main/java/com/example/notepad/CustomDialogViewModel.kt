package com.example.notepad

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notepad.data.PrefsManagerImp

class CustomDialogViewModel(app: Application) : AndroidViewModel(app) {

    private val prefManager: IPrefsManager = PrefsManagerImp(app)

    private val dataFromPrefs: MutableLiveData<ToDoItem> = MutableLiveData()
    val dataFromPrefsResult: LiveData<ToDoItem> = dataFromPrefs

    fun getTitleAndDescriptionFromPrefs(titleKey: String, descriptionKey: String) {
        val title = prefManager.getStringFromPrefs(titleKey)
        val description = prefManager.getStringFromPrefs(descriptionKey)
        dataFromPrefs.postValue(ToDoItem(0, title, description))
    }

    fun saveStringInPrefs(key: String, value: String) {
        prefManager.saveStringInPrefs(key, value)
    }

}