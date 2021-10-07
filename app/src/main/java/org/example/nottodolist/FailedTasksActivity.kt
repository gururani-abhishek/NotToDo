package org.example.nottodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FailedTasksActivity : AppCompatActivity(), IDeletedNotesRVAdapter{

    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_failed_tasks)
        val layout = findViewById<ConstraintLayout>(R.id.layoutFTasks)
        layout.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_700))

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView1)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = DeletedNotesRVAdapter(this, this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allDeletedNote.observe(this, Observer {list ->
            list?.let {
                adapter.onUpdate(it)
            }
        })

    }

    override fun onItemClicked(note: DeletedNote) {
        val text = note.text
        viewModel.insertNote(Note(text))
        viewModel.deleteD(note)
    }
}