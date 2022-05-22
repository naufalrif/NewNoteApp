package com.example.newnoteapp.notes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Notes::class], version = 1)
abstract class NotesDB : RoomDatabase() {
    abstract fun notesDao() : NotesDao
    companion object{
        private var INSTANCE : NotesDB? = null
        fun getInstance(context : Context):NotesDB? {
            if (INSTANCE == null){
                synchronized(NotesDB::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        NotesDB::class.java,"Notes.db").allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }
}