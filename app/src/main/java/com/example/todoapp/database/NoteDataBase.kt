package com.example.todoapp.database

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.Utils.CommonFunction.DATABASE_NAME
import com.example.todoapp.model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NoteDataBase : RoomDatabase() {

    abstract fun notesDAO(): NoteDAO

    companion object {
        @Volatile
        private var INSTANCE: NoteDataBase? = null
        fun getDatabase(context: Context): NoteDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDataBase::class.java,
                    DATABASE_NAME
                ).allowMainThreadQueries().build()

                INSTANCE = instance

                return instance
            }
        }
    }
}