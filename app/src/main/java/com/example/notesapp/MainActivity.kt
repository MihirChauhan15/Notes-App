package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(),NotesItemClicked {
    private lateinit var  mNotesAdapter:NotesAdapter
    lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView :RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        mNotesAdapter= NotesAdapter(this,this)

        recyclerView.adapter = mNotesAdapter

        viewModel =ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NotesViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->
            list?.let{
                mNotesAdapter.updateNotes(it)
            }
        })
    }

    override fun OnItemClicked(notes: Notes) {
        viewModel.delete(notes)
        Toast.makeText(applicationContext,"${notes.text} is deleted successfully",Toast.LENGTH_SHORT).show()
    }

    fun insertNotes(view: View) {
        val editText = findViewById<EditText>(R.id.editTextTextPersonName)
        val notesText = editText.text.toString()
       if(!notesText.isEmpty()){
           viewModel.insert(Notes(notesText))
           Toast.makeText(applicationContext,"$notesText is inserted successfully",Toast.LENGTH_SHORT).show()
           editText.setText("")
       }

    }

}