package com.example.todoapp.views

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.R
import com.example.todoapp.Utils.CommonFunction.currentDate
import com.example.todoapp.Utils.CommonFunction.onButtonClicked
import com.example.todoapp.databinding.ActivityEditNotesBinding
import com.example.todoapp.model.Notes
import com.example.todoapp.viewModel.AddNoteViewModelFactory
import com.example.todoapp.viewModel.NoteViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class EditNotes : AppCompatActivity() {

    private lateinit var binding: ActivityEditNotesBinding
    private var statusCode: Int = 0
    private lateinit var notesViewModel: NoteViewModel
    private var noteId: Int = 0
    private lateinit var noteTitle: String
    private lateinit var noteContent: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_notes)

        noteId = intent.getIntExtra("noteId", 0)
        noteTitle = intent.getStringExtra("noteTitle").toString()
        noteContent = intent.getStringExtra("noteContent").toString()
        statusCode = intent.getIntExtra("noteStatus", 0)

        val addNoteViewModelFactory = AddNoteViewModelFactory(application)
        notesViewModel = ViewModelProvider(this, addNoteViewModelFactory)[NoteViewModel::class.java]
        binding.editNoteViewModel = notesViewModel
        binding.lifecycleOwner = this

        binding.etTitle.setText(noteTitle)
        binding.etNote.setText(noteContent)


        binding.btnCompleted.setOnClickListener {
            statusCode = onButtonClicked(binding.btnCompleted, 1)
            Log.d("TAG","$statusCode")
        }
        binding.btnDoing.setOnClickListener {
            statusCode = onButtonClicked(binding.btnDoing, 2)
            Log.d("TAG","$statusCode")
        }
        binding.btnPending.setOnClickListener {
            statusCode = onButtonClicked(binding.btnPending, 3)
            Log.d("TAG","$statusCode")
        }
        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        binding.deleteButton.setOnClickListener {
            deleteNote()
        }

        binding.btnEditNote.setOnClickListener {
            updateNote()
        }
    }

    private fun updateNote() {
        val title = binding.etTitle.text.toString()
        val note = binding.etNote.text.toString()

        Log.d("TAG-------","$statusCode")


        val data = Notes(
            id = noteId.toInt(),
            title = title,
            note = note,
            date = currentDate(),
            status = statusCode
        )

        notesViewModel.updateNote(data)
        onBackPressed()
    }

    private fun deleteNote() {
        val data = Notes(
            id = noteId.toInt(),
            title = noteTitle,
            note = noteContent,
            date = currentDate(),
            status = statusCode
        )
        notesViewModel.deleteNote(data)
        onBackPressed()
    }
}