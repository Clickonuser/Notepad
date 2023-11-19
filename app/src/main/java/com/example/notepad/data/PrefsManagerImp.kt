package com.example.notepad.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.notepad.Constants.BLANK_VALUE
import com.example.notepad.Constants.PREFS_NAME
import com.example.notepad.IPrefsManager

/**
 * Manager that handles logic with shared preferences
 */
class PrefsManagerImp(app: Application) : IPrefsManager {

    private val sharedPref: SharedPreferences = app.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun getStringFromPrefs(key: String): String {
        return sharedPref.getString(key, BLANK_VALUE) ?: BLANK_VALUE
    }

    override fun saveStringInPrefs(key: String, value: String) {
        with (sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }

}