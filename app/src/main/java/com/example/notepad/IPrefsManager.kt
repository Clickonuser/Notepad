package com.example.notepad

interface IPrefsManager {

    fun getStringFromPrefs(key: String) : String

    fun saveStringInPrefs(key: String, value: String)

}