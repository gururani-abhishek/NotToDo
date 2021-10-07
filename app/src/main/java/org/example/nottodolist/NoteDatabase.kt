package org.example.nottodolist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class, DeletedNote::class], version = 1, exportSchema = false)
public abstract class NoteDatabase : RoomDatabase() {   //out class should be abstract and it should extent RoomDatabase() class

    abstract fun noteDao(): NoteDao
    abstract fun deletedNoteDao() : DeletedNoteDao

    //companion objects are like static members of a class which can be accessed
    //without creating an object of the class

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
