package org.example.nottodolist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "deletedNotes_table")
class DeletedNote (@ColumnInfo(name = "deletedNote_text") val text : String) {
    @PrimaryKey(autoGenerate = true) var id = 0
}