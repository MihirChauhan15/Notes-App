package com.example.notesapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {

    @Insert( onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note:Notes)

    @Delete
    suspend fun delete(note:Notes)

    @Query("SELECT * FROM notes_table ORDER BY text ASC")
    fun getAllNotes():LiveData<List<Notes>>
}