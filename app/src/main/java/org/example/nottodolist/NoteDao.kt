package org.example.nottodolist

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.concurrent.Flow

@Dao //this annotation identifies it as a DAO class for ROOM
interface NoteDao {
    //we must process our insert and delete queries on a separate thread.
    //as they are long and time consuming processes, and we don't need our application to
    //freeze.

    @Insert(onConflict = OnConflictStrategy.IGNORE) //special DAO method annotation
    suspend fun insert(note : Note)  //Associated method

    @Delete //special DAO method annotation
    suspend fun delete(note : Note)  //Associated method

    @Query("select * from notes_table order by id asc")  //SQL query
    fun getAllNote() : LiveData<List<Note>>  //Associated method

    @Query("DELETE From notes_table")
    fun deleteAllNote()



    /*
    What is live data?
    In simple terms, Live data is an observable data holder class — meaning livedata can
    hold a set of data that can be observed from other android components like activities,
    fragments and services.
    */
}