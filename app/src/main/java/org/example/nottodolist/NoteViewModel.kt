package org.example.nottodolist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel (application: Application) : AndroidViewModel(application) {
    val allNotes : LiveData<List<Note>>
    val allDeletedNote : LiveData<List<DeletedNote>>
    private val repository : NoteRepository
    private val deletedNoteRepository: DeletedNoteRepository

    //init block is executed after the primary constructor, there can be more than one init{}, they all will be
    //executed sequentially.
    init {
        val dao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(dao)
        val ddao = NoteDatabase.getDatabase(application).deletedNoteDao()
        deletedNoteRepository = DeletedNoteRepository(ddao)
        allNotes = dao.getAllNote()
        allDeletedNote = ddao.getAllDeletedNote()
    }

    fun deleteNote(note : Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertDeletedNote(note : DeletedNote) = viewModelScope.launch(Dispatchers.IO) {
        deletedNoteRepository.insertD(note)
    }

    fun deleteD(note :DeletedNote) = viewModelScope.launch(Dispatchers.IO) {
        deletedNoteRepository.deleteD(note)
    }
    fun insertNote(note : Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
    fun deleteAllNote() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllNotes()
    }

    fun deleteAllDNote() = viewModelScope.launch(Dispatchers.IO){
        deletedNoteRepository.deleteAllDNote()
    }

}