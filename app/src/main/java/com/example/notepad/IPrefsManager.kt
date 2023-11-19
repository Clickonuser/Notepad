package com.example.notepad

interface IPrefsManager {

    /**
     * Return String from prefs
     */
    fun getStringFromPrefs(key: String) : String

    /**
     * Saving String to prefs
     */
    fun saveStringInPrefs(key: String, value: String)

}