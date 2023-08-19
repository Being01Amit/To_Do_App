package com.example.todoapp.database

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData
import com.example.todoapp.model.Notes


class NotesRepository(private val noteDAO: NoteDAO) {

    val allNotes : LiveData<List<Notes>> = noteDAO.getAllTasks()

    fun insertNotes(notes: Notes) {
        return noteDAO.insertTask(notes)
    }

    fun deleteNotes(notes: Notes) {
        return noteDAO.deleteTask(notes)
    }

    fun updateNotes(notes: Notes) {
        return noteDAO.updateTask(notes.id!!, notes.title!!, notes.note!!)
    }
}