package com.example.todoapp.views.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.model.Notes

class NotesAdapter() : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private val notesList = ArrayList<Notes>()
    private lateinit var itemListener: onItemClickListener

    inner class ViewHolder(itemView: View, itemListIntents: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.txt_title)
        private val note: TextView = itemView.findViewById(R.id.txt_note)
        private val date: TextView = itemView.findViewById(R.id.txt_date)
        private val status: TextView = itemView.findViewById(R.id.txt_status)

        fun bind(noteData: Notes) {
            title.text = noteData.title
            note.text = noteData.note
            date.text = noteData.date
            when (noteData.status) {
                1 -> status.text ="Doing"
                2 -> status.text = "Completed"
                3 -> status.text ="Pending"
                else -> status.text = "Pending"
            }
        }

        init {
            itemView.setOnClickListener {
                itemListIntents.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)

        return ViewHolder(view, itemListener)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = notesList[position]
        holder.bind(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(documents: ArrayList<Notes>) {
        notesList.clear()
        notesList.addAll(documents)
        notifyDataSetChanged()
    }

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        itemListener = listener
    }
}