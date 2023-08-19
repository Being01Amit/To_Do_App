package com.example.todoapp.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.views.AddNoteActivity

class AddNoteViewModelFactory(private val addNoteActivity: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return NoteViewModel(addNoteActivity) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}