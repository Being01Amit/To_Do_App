package com.example.todoapp.database

import android.app.Application
import androidx.room.Room

class NotesApplication : Application() {
    companion object {
        lateinit var database: NoteDataBase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, NoteDataBase::class.java, "todo-db").build()
    }
}