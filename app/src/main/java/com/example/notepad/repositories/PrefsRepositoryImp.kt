package com.example.notepad.repositories

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.notepad.Constants.BLANK_VALUE
import com.example.notepad.Constants.PREFS_NAME

/**
 * Manager that handles logic with shared preferences
 */
class PrefsRepositoryImp(app: Application) : PrefsRepository {

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

    override fun getIntFromPrefs(key: String): Int {
        return sharedPref.getInt(key, 1)
    }

    override fun saveIntInPrefs(key: String, value: Int) {
        with (sharedPref.edit()) {
            putInt(key, value)
            apply()
        }
    }

}