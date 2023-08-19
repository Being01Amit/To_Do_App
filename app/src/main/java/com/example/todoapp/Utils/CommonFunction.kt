package com.example.todoapp.Utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.widget.Button
import android.widget.Toast
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SuppressLint("StaticFieldLeak")
object CommonFunction {

    const val DATABASE_NAME = "note_database"
    private var lastClickedButton: Button? = null

    fun toast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT)
            .show()
    }

    fun onButtonClicked(clickedButton: Button, clicked: Int): Int {
        lastClickedButton?.setBackgroundColor(Color.parseColor("#2196f3"))
        lastClickedButton = clickedButton

        clickedButton.setBackgroundColor(Color.GREEN)
        Log.d("TAG", "$clicked")
        return clicked
    }

//    This function is used to fetch the current date
    fun currentDate(): String {
        val currentDate: LocalDate = LocalDate.now()
        val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return dateFormat.format(currentDate)
    }

//    Here are checking the if the Title or Note is empty or not
    fun checkTitleAndNote(context: Context, title: String, note: String): Boolean {

    return when {
        title.isEmpty() -> {
            toast(context, "Please enter Title")
            false
        }

        note.isEmpty() -> {
            toast(context, "Please enter Note")
            false
        }

        else -> {
            true
        }
    }
    }
}