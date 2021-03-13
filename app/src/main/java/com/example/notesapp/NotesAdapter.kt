package com.example.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(val context: Context,val listener:NotesItemClicked):RecyclerView.Adapter<NotesViewHolder>() {
    private val allNotes=ArrayList<Notes>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val viewHolder =NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_item,parent,false))
        viewHolder.deleteButton.setOnClickListener{
            listener.OnItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currNotes = allNotes[position]
        holder.textView.text = currNotes.text
    }
    fun updateNotes(newNotes: List<Notes>){
        allNotes.clear()
        allNotes.addAll(newNotes)

        notifyDataSetChanged()
    }
}
class NotesViewHolder(notesView: View):RecyclerView.ViewHolder(notesView){
    val textView = notesView.findViewById<TextView>(R.id.textView)
    val deleteButton = notesView.findViewById<ImageView>(R.id.imageView)
}

interface NotesItemClicked{
    fun OnItemClicked(notes: Notes)
}