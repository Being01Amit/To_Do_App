package com.example.todoapp.views

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityAddNoteBinding
import com.example.todoapp.databinding.ActivityAllNotesBinding
import com.example.todoapp.model.Notes
import com.example.todoapp.viewModel.AddNoteViewModelFactory
import com.example.todoapp.viewModel.NoteViewModel
import com.example.todoapp.views.adapter.NotesAdapter

class AllNotes : AppCompatActivity() {
    private lateinit var binding: ActivityAllNotesBinding
    private lateinit var notesViewModel: NoteViewModel
    private var notes : ArrayList<Notes> = ArrayList()
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_notes)

        val notesAdapter = NotesAdapter()
        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.notesRecyclerView.adapter = notesAdapter

        val addNoteViewModelFactory = AddNoteViewModelFactory(application)
        notesViewModel = ViewModelProvider(this, addNoteViewModelFactory)[NoteViewModel::class.java]
        binding.addNoteViewModel = notesViewModel
        binding.lifecycleOwner = this

        binding.btnAddNote.setOnClickListener {
            val intent = Intent(this@AllNotes,AddNoteActivity::class.java)
            startActivity(intent)
        }

        notesViewModel.allNotes.observe(this@AllNotes) { noteList ->
            notes.clear()

            for (i in noteList.indices) {
                val notesData = Notes()
                notesData.id = noteList[i].id
                notesData.title = noteList[i].title.toString()
                notesData.note = noteList[i].note.toString()
                notesData.date = noteList[i].date
                notesData.status = noteList[i].status
                notes.add(notesData)
            }
            notesAdapter.notifyDataSetChanged()
            notesAdapter.setData(notes)
            notesAdapter.setOnItemClickListener(object : NotesAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    val intent = Intent(this@AllNotes, EditNotes::class.java)
                    intent.putExtra("noteId", notes[position].id)
                    intent.putExtra("noteTitle", notes[position].title)
                    intent.putExtra("noteContent", notes[position].note)
                    intent.putExtra("noteStatus", notes[position].status)
                    startActivity(intent)
                }
            })

        }
    }
}