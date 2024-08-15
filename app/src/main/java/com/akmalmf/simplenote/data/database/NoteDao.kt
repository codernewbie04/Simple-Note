package com.akmalmf.simplenote.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.akmalmf.simplenote.data.model.Note

/**
 * Created by Akmal Muhamad Firdaus on 2024/08/15 17:13
 * akmalmf007@gmail.com
 */
@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    suspend fun insertWithTimestamp(note: Note) {
        insert(note.apply{
            createdAt = System.currentTimeMillis()
        })
    }

    suspend fun updateWithTimestamp(note: Note) {
        update(note.apply{
            createdAt = System.currentTimeMillis()
        })
        // it will update the timestamp of the note
    }

    @Delete
    suspend fun delete(note: Note)
}