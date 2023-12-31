package com.example.notepad.repositories

import android.content.SharedPreferences
import com.example.notepad.Constants.BLANK_VALUE_INT
import com.example.notepad.Constants.BLANK_VALUE_STRING
import javax.inject.Inject

/**
 * Manager that handles logic with shared preferences
 */
class PrefsRepositoryImpl @Inject constructor(
    private val sharedPref: SharedPreferences
) : PrefsRepository {

    override fun getStringFromPrefs(key: String): String {
        return sharedPref.getString(key, BLANK_VALUE_STRING) ?: BLANK_VALUE_STRING
    }

    override fun saveStringInPrefs(key: String, value: String) {
        with (sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }

    override fun getIntFromPrefs(key: String): Int {
        return sharedPref.getInt(key, BLANK_VALUE_INT)
    }

    override fun saveIntInPrefs(key: String, value: Int) {
        with (sharedPref.edit()) {
            putInt(key, value)
            apply()
        }
    }

}