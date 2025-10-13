package com.example.notes.ui.main

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.model.Note

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null

    private val binding
        get() = _binding ?: throw IllegalStateException("ActivityMainBinding can't be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val notes: MutableList<Note> = mutableListOf<Note>()
        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        val adapter = NoteAdapter(notes)
        binding.rvNotes.adapter = adapter
        binding.rvNotes.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

//        val todos: MutableList<String> = mutableListOf()
//        val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, todos)
//        binding.lvNotes.adapter = adapter

        with(binding) {
//            lvNotes.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
//                val text = lvNotes.getItemAtPosition(position).toString()
//                adapter.remove(text)
//
//                Toast.makeText(this@MainActivity, "Note deleted", Toast.LENGTH_LONG).show()
//            }

            btnNoteAdd.setOnClickListener {
                val text: String = etNoteText.text.toString().trim()
                if (text.isNotEmpty()) {
                    val newNote = Note(content = text)
                    adapter.addNote(newNote)
                    rvNotes.scrollToPosition(0)
                    Toast.makeText(this@MainActivity, "Note added", Toast.LENGTH_SHORT).show()
                    etNoteText.text.clear()
                } else {
                    Toast.makeText(this@MainActivity, "Note is empty", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}