package com.example.todoapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.model.Notes

@Dao
interface NoteDAO {

    @Query("SELECT * FROM notes_table")
    fun getAllTasks(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Notes)

    @Delete
    fun deleteTask(task : Notes)

    @Query("UPDATE notes_table SET title = :title,note = :note WHERE id = :id")
    fun updateTask(id : Int? , title: String?, note: String?)

}