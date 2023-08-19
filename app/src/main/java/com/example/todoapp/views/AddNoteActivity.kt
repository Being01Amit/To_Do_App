package com.example.todoapp.views

import android.graphics.Color
import android.icu.text.DateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityAddNoteBinding
import com.example.todoapp.Utils.CommonFunction.checkTitleAndNote
import com.example.todoapp.Utils.CommonFunction.currentDate
import com.example.todoapp.Utils.CommonFunction.onButtonClicked
import com.example.todoapp.Utils.CommonFunction.toast
import com.example.todoapp.model.Notes
import com.example.todoapp.viewModel.AddNoteViewModelFactory
import com.example.todoapp.viewModel.NoteViewModel
import java.sql.Date
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    private var statusCode: Int = 0
    private lateinit var notesViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note)

        val addNoteViewModelFactory = AddNoteViewModelFactory(application)
        notesViewModel = ViewModelProvider(this, addNoteViewModelFactory)[NoteViewModel::class.java]
        binding.addNoteViewModel = notesViewModel
        binding.lifecycleOwner = this

        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        binding.btnDoing.setOnClickListener {
            statusCode = onButtonClicked(binding.btnDoing, 1)
        }
        binding.btnCompleted.setOnClickListener {
            statusCode = onButtonClicked(binding.btnCompleted, 2)
        }

        binding.btnPending.setOnClickListener {
            statusCode = onButtonClicked(binding.btnPending, 3)
        }

        binding.btnAddNote.setOnClickListener {
            if (checkTitleAndNote(
                    this,
                    binding.etTitle.text.toString(),
                    binding.etNote.text.toString()
                )
            ) {
                createNote(it)
            }
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun createNote(view: View) {
        val title = binding.etTitle.text.toString()
        val note = binding.etNote.text.toString()

        val data = Notes(
            id = null,
            title = title,
            note = note,
            date = currentDate(),
            status =  statusCode
        )
        notesViewModel.insert(data)
        toast(this, "Notes Created Successfully")
        onBackPressed()
    }
}