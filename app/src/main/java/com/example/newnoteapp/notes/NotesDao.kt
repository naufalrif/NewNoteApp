package com.example.newnoteapp.notes

import androidx.room.*

@Dao
interface NotesDao {
    @Insert
    fun insertNote(notes: Notes) : Long
    @Query("SELECT * FROM Notes")
    fun getNoteData() : List<Notes>
    @Delete
    fun deleteNotesData(notes: Notes) :Int
    @Update
    fun editNotesData(notes: Notes) : Int
}