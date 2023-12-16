package com.example.notepad

import com.example.notepad.repositories.RoomRepositoryImpl
import com.example.notepad.room.ToDoDao
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class RoomRepositoryImplTest {

    private lateinit var subject: RoomRepositoryImpl
    private val toDoDaoMock: ToDoDao = mock()

    private val fakeItemOne = ToDoItem(0, "testTitleOne", "testDescriptionOne")
    private val fakeItemTwo = ToDoItem(1, "testTitleTwo", "testDescriptionTwo")

    private val fakeList: List<ToDoItem> = listOf(
        fakeItemOne,
        fakeItemTwo,
    )

    @Before
    fun setup() {
        subject = RoomRepositoryImpl(toDoDaoMock)
    }

    @Test
    fun getAllItems_success() {
        `when`(toDoDaoMock.getAllItems()).thenReturn(fakeList)
        val result = subject.getAllItems()
        assertEquals(2, result.size)
    }

    @Test
    fun insertItem_success() {
        subject.insertItem(fakeItemOne)
        verify(toDoDaoMock, times(1)).insertItem(fakeItemOne)
    }

    @Test
    fun updateItem_success() {
        subject.updateItem(fakeItemOne)
        verify(toDoDaoMock, times(1)).updateItem(fakeItemOne)
    }

    @Test
    fun deleteItem_success() {
        subject.deleteItem(fakeItemOne)
        verify(toDoDaoMock, times(1)).deleteItem(fakeItemOne)
    }

}

