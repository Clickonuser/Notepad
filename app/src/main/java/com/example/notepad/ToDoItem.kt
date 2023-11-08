package com.example.notepad

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ToDoItem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "titleColumn") val title: String,
    val description: String,
)
