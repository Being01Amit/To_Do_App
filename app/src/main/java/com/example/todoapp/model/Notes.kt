package com.example.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes_table")
data class Notes(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "note") var note: String? = null,
    @ColumnInfo(name = "date") var date: String? = null,
    @ColumnInfo(name = "status") var status: Int? = null,
)
