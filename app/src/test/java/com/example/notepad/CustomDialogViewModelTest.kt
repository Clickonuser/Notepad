package com.example.notepad

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.notepad.repositories.PrefsRepository
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class CustomDialogViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var subject: CustomDialogViewModel
    private val prefRepository: PrefsRepository = mock()

    private val fakeString = "fake"
    private val fakeInt = 0

    private val keyTest = "keyTest"
    private val stringValueTest = "stringValueTest"
    private val intValueTest = 0

    @Before
    fun setup() {
        subject = CustomDialogViewModel(prefRepository)
    }

    @Test
    fun getTitleAndDescriptionFromPrefs_success() {
        `when`(prefRepository.getStringFromPrefs(keyTest)).thenReturn(fakeString)

        subject.getTitleAndDescriptionFromPrefs(keyTest, keyTest)

        val expectedResultTitle = subject.dataFromPrefsResult.value?.title
        val expectedResultDescription = subject.dataFromPrefsResult.value?.description
        assertEquals("fake", expectedResultTitle)
        assertEquals("fake", expectedResultDescription)
    }

    @Test
    fun saveStringInPrefs_success() {
        subject.saveStringInPrefs(keyTest, stringValueTest)
        verify(prefRepository, times(1)).saveStringInPrefs(keyTest, stringValueTest)
    }

    @Test
    fun saveIntInPrefs_success() {
        subject.saveIntInPrefs(keyTest, intValueTest)
        verify(prefRepository, times(1)).saveIntInPrefs(keyTest, intValueTest)
    }

    @Test
    fun getIdFromPrefs_success() {
        `when`(prefRepository.getIntFromPrefs(keyTest)).thenReturn(fakeInt)

        subject.getIdFromPrefs(keyTest)

        val expectedResult = subject.idFromPrefsResult.value
        assertEquals(0, expectedResult)
    }

}

