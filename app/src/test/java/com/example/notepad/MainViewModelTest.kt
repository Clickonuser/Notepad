package com.example.notepad

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.notepad.repositories.RoomRepository
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var subject: MainViewModel
    private val roomRepository: RoomRepository = mock()

    private val fakeItemOne = ToDoItem(0, "testTitleOne", "testDescriptionOne")
    private val fakeItemTwo = ToDoItem(1, "testTitleTwo", "testDescriptionTwo")
    private val fakeItemThreeForInsert = ToDoItem(2, "testTitleThree", "testDescriptionThree")
    private val fakeItemFourForUpdate = ToDoItem(0, "testTitleFour", "testDescriptionFour")

    private val fakeList: MutableList<ToDoItem> = mutableListOf(
        fakeItemOne,
        fakeItemTwo,
    )


    @Before
    fun setup() {
        subject = MainViewModel(roomRepository)
    }

    @Test
    fun getAllItems_success() {
        `when`(roomRepository.getAllItems()).thenReturn(fakeList)

        subject.getAllItems()

        val expectedResult = subject.todoItemListResult.value?.size
        val firstItem = subject.todoItemListResult.value?.first()
        assertEquals(2, expectedResult)
        assertEquals(fakeItemOne, firstItem)
    }

    @Test
    fun insertItem_success() {
        `when`(roomRepository.getAllItems()).thenReturn(fakeList)

        subject.getAllItems()

        val expectedResult = subject.todoItemListResult.value?.size
        assertEquals(2, expectedResult)

        subject.insertItem(fakeItemThreeForInsert)

        val expectedResultAfterInsert = subject.todoItemListResult.value?.size
        val lastItem = subject.todoItemListResult.value?.last()
        assertEquals(3, expectedResultAfterInsert)
        assertEquals(fakeItemThreeForInsert, lastItem)
        verify(roomRepository, times(1)).insertItem(fakeItemThreeForInsert)
    }

    @Test
    fun insertItem_undo_success() {
        `when`(roomRepository.getAllItems()).thenReturn(fakeList)

        subject.getAllItems()

        val expectedResult = subject.todoItemListResult.value?.size
        assertEquals(2, expectedResult)

        subject.deleteItem(fakeItemOne)

        val expectedResultAfterDelete = subject.todoItemListResult.value?.size
        val expectedFirstItemAfterDelete = subject.todoItemListResult.value?.first()
        assertEquals(1, expectedResultAfterDelete)
        assertEquals(fakeItemTwo, expectedFirstItemAfterDelete)

        subject.insertItem(fakeItemOne)

        val expectedResultAfterInsert = subject.todoItemListResult.value?.size
        val expectedFirstItemAfterInsertUndo = subject.todoItemListResult.value?.first()
        assertEquals(2, expectedResultAfterInsert)
        assertEquals(fakeItemOne, expectedFirstItemAfterInsertUndo)
        verify(roomRepository, times(1)).insertItem(fakeItemOne)
    }

    @Test
    fun updateItem_success() {
        `when`(roomRepository.getAllItems()).thenReturn(fakeList)

        subject.getAllItems()

        subject.updateItem(fakeItemFourForUpdate)

        val updatedItem = subject.todoItemListResult.value?.first()
        assertEquals("testTitleFour", updatedItem?.title)
        assertEquals("testDescriptionFour", updatedItem?.description)
        verify(roomRepository, times(1)).updateItem(fakeItemFourForUpdate)
    }

    @Test
    fun deleteItem_success() {
        `when`(roomRepository.getAllItems()).thenReturn(fakeList)

        subject.getAllItems()

        val expectedResult = subject.todoItemListResult.value?.size
        assertEquals(2, expectedResult)

        subject.deleteItem(fakeItemTwo)

        val expectedResultAfterDelete = subject.todoItemListResult.value?.size
        assertEquals(1, expectedResultAfterDelete)
        verify(roomRepository, times(1)).deleteItem(fakeItemTwo)
    }

}

