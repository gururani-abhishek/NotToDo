package org.example.nottodolist

import androidx.lifecycle.LiveData

class DeletedNoteRepository(private val deletedNoteDao : DeletedNoteDao) {
    val allDeletedNote : LiveData<List<DeletedNote>> = deletedNoteDao.getAllDeletedNote()

    suspend fun insertD(note : DeletedNote) {
        deletedNoteDao.insertD(note)
    }

    suspend fun deleteD(note : DeletedNote) {
        deletedNoteDao.deleteD(note)
    }
    suspend fun deleteAllDNote() {
        deletedNoteDao.deleteAllDNote()
    }

}