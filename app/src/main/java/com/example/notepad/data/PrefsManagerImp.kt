package com.example.notepad.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.notepad.IPrefsManager

class PrefsManagerImp(app: Application) : IPrefsManager {

    private val sharedPref: SharedPreferences = app.getSharedPreferences("preferences", Context.MODE_PRIVATE)

    override fun getStringFromPrefs(key: String): String {
        return sharedPref.getString(key, "") ?: ""
    }

    override fun saveStringInPrefs(key: String, value: String) {
        with (sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }

}