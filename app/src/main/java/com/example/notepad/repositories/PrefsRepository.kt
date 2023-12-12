package com.example.notepad.repositories

interface PrefsRepository {

    /**
     * Return String from prefs
     */
    fun getStringFromPrefs(key: String) : String

    /**
     * Saving String to prefs
     */
    fun saveStringInPrefs(key: String, value: String)

}