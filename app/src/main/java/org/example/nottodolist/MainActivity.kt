package org.example.nottodolist

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get

class MainActivity : AppCompatActivity() {
    lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layout = findViewById<ConstraintLayout>(R.id.layout)
        layout.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_700))

      noteViewModel= ViewModelProvider(this,
      ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

    }

    fun submitData(view: android.view.View) {
        val input = findViewById<EditText>(R.id.addNTD)
        val noteText = input.text.toString()
        if(noteText.isNotEmpty()) {
            noteViewModel.insertNote(Note(noteText))
            Toast.makeText(this, "added, sooooo therapeutic!!", Toast.LENGTH_LONG).show()
            input.setText("")
        }
    }

    fun openTasks(view: android.view.View) {
        val intent = Intent(this, TasksActivity::class.java)
        startActivity(intent)
    }

    fun openFailedTasks(view: android.view.View) {
        val intent = Intent(this, FailedTasksActivity::class.java)
        startActivity(intent)

    }

    fun resetAll(view: android.view.View) {
        noteViewModel.deleteAllNote()
        noteViewModel.deleteAllDNote()
    }
}