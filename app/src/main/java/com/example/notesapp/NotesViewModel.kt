package com.example.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application):AndroidViewModel(application) {
    val  allNotes:LiveData<List<Notes>>
    val repository:NotesRepository
    init{
        val dao =NotesRoomDatabase.getDatabase(application).getNoteDao()
        repository = NotesRepository(dao)
        allNotes = repository.allNotes
    }
    fun delete(notes:Notes) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(notes)
    }
    fun insert(notes:Notes) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(notes)
    }
}