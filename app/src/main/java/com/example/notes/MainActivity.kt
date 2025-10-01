package com.example.notes

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null

    private val binding
        get() = _binding ?: throw IllegalStateException("ActivityMainBinding can't be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val todos: MutableList<String> = mutableListOf()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, todos)

        binding.lvNotes.adapter = adapter

        with(binding) {
            lvNotes.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
                val text = lvNotes.getItemAtPosition(position).toString()
                adapter.remove(text)

                Toast.makeText(this@MainActivity, "Note deleted", Toast.LENGTH_LONG).show()
            }

            btnNoteAdd.setOnClickListener {
                val text: String = etNoteText.text.toString().trim()
                if (text.isNotEmpty()) {
                    adapter.insert(text, 0)
                    Toast.makeText(this@MainActivity, "Note added", Toast.LENGTH_LONG).show()
                    etNoteText.text.clear()
                } else {
                    Toast.makeText(this@MainActivity, "Note is empty", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}