package com.example.todoapp.viewModel

import android.app.Application
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todoapp.database.NoteDAO
import com.example.todoapp.database.NoteDataBase
import com.example.todoapp.database.NotesApplication
import com.example.todoapp.database.NotesRepository
import com.example.todoapp.model.Notes
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NotesRepository
    val allNotes: LiveData<List<Notes>>

    init {
        val dao = NoteDataBase.getDatabase(application).notesDAO()
        repository = NotesRepository(dao)
        allNotes = repository.allNotes

    }

    fun insert(note: Notes) {
        repository.insertNotes(note)
    }

    fun getNotes(note: Notes): LiveData<List<Notes>> = repository.allNotes

    fun deleteNote(note : Notes) {
        repository.deleteNotes(note)
    }

    fun updateNote(note: Notes) {
        repository.updateNotes(note)
    }
}