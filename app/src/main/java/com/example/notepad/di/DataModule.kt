package com.example.notepad.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.notepad.Constants.DATABASE_NAME
import com.example.notepad.Constants.PREFS_NAME
import com.example.notepad.repositories.PrefsRepository
import com.example.notepad.repositories.PrefsRepositoryImpl
import com.example.notepad.repositories.RoomRepository
import com.example.notepad.repositories.RoomRepositoryImpl
import com.example.notepad.room.AppDatabase
import com.example.notepad.room.ToDoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideRoomDataBase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, DATABASE_NAME
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideTodoDao(db: AppDatabase): ToDoDao = db.todoDao()

    @Singleton
    @Provides
    fun provideRoomRepository(toDoDao: ToDoDao): RoomRepository = RoomRepositoryImpl(toDoDao)

    @Singleton
    @Provides
    fun provideSharedPref(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun providePrefRepository(sharedPref: SharedPreferences): PrefsRepository =
        PrefsRepositoryImpl(sharedPref)

}