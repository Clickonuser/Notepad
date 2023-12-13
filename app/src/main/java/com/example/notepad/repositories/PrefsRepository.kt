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

    /**
     * Return id from prefs
     */
    fun getIntFromPrefs(key: String) : Int

    /**
     * Saving id to prefs
     */
    fun saveIntInPrefs(key: String, value: Int)

}