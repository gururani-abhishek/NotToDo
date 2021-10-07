package org.example.nottodolist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*I'm creating an entity, and entity is a table.
to make my Note class meaningful, I need to use kotlin annotations. Specific annotations are used
which prompt ROOM(an android architecture component, a SQLite database framework)
to generate the required code. */

@Entity(tableName = "notes_table")
class Note(@ColumnInfo(name = "note_text") val text : String) {
    @PrimaryKey(autoGenerate = true) var id = 0
    //each room entity must define a primary key, a primary key is unique to one single
    //row. Here I've set autoGenerate property of primary key to true.
}