package org.example.nottodolist

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DeletedNoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertD(note : DeletedNote)

    @Delete
    suspend fun deleteD(note : DeletedNote)

    @Query("select * from deletedNotes_table order by id asc")
    fun getAllDeletedNote() : LiveData<List<DeletedNote>>

    @Query("DELETE FROM deletedNotes_table")
    fun deleteAllDNote()
}