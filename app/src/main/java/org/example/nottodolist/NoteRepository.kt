package org.example.nottodolist

import androidx.lifecycle.LiveData

/* repository isn't the part of android architecture component but is highly recommended
as it provides a clean API to communicate with.*/

//it will require a private property of type NoteDao as it, it only needs to interact with
//DAO and not the whole database
class NoteRepository(private val noteDao : NoteDao) {

    val allNote : LiveData<List<Note>> = noteDao.getAllNote()

    suspend fun insert(note : Note) {
        noteDao.insert(note)
    }
    suspend fun delete(note : Note) {
        noteDao.delete(note)
    }

    suspend fun deleteAllNotes() {
        noteDao.deleteAllNote()
    }

}