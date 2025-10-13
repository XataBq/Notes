package com.example.notes.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.model.Note
import com.example.notes.R

class NoteAdapter(private val notes: MutableList<Note>) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView: TextView = itemView.findViewById(R.id.tvTitle)
        val contentView: TextView = itemView.findViewById(R.id.tvContent)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: NoteViewHolder,
        position: Int
    ) {
        val note = notes[position]
        holder.titleView.text = note.title
        holder.contentView.text = note.content

        holder.itemView.setOnClickListener {
            notes.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun addNote(note: Note) {
        notes.add(0, note)
        notifyItemInserted(0)
    }

}