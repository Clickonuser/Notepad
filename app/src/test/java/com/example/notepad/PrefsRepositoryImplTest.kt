package com.example.notepad

import android.content.SharedPreferences
import com.example.notepad.repositories.PrefsRepositoryImpl
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class PrefsRepositoryImplTest {

    private lateinit var subject: PrefsRepositoryImpl
    private val sharedPrefsMock: SharedPreferences = mock()
    private val editorMock: SharedPreferences.Editor = mock()

    private val fakeString = "fake"
    private val fakeInt = 0

    private val keyTest = "keyTest"
    private val stringValueTest = "stringValueTest"
    private val intValueTest = 0

    @Before
    fun setup() {
        subject = PrefsRepositoryImpl(sharedPrefsMock)
    }

    @Test
    fun getStringFromPrefs_success() {
        `when`(
            sharedPrefsMock.getString(
                keyTest,
                Constants.BLANK_VALUE_STRING
            )
        ).thenReturn(fakeString)

        val result = subject.getStringFromPrefs(keyTest)
        assertEquals(fakeString, result)
    }

    @Test
    fun saveStringInPrefs_success() {
        `when`(sharedPrefsMock.edit()).thenReturn(editorMock)
        subject.saveStringInPrefs(keyTest, stringValueTest)
        verify(editorMock, times(1)).putString(keyTest, stringValueTest)
    }

    @Test
    fun getIntFromPrefs_success() {
        `when`(
            sharedPrefsMock.getInt(
                keyTest,
                Constants.BLANK_VALUE_INT
            )
        ).thenReturn(fakeInt)

        val result = subject.getIntFromPrefs(keyTest)
        assertEquals(fakeInt, result)
    }

    @Test
    fun saveIntInPrefs_success() {
        `when`(sharedPrefsMock.edit()).thenReturn(editorMock)
        subject.saveIntInPrefs(keyTest, intValueTest)
        verify(editorMock, times(1)).putInt(keyTest, intValueTest)
    }
}


