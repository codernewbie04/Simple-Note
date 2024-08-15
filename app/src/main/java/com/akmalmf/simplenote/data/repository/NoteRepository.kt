package com.akmalmf.simplenote.data.repository

import androidx.lifecycle.LiveData
import com.akmalmf.simplenote.data.database.NoteDao
import com.akmalmf.simplenote.data.model.Note

/**
 * Created by Akmal Muhamad Firdaus on 2024/08/15 17:15
 * akmalmf007@gmail.com
 */
class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDao.insertWithTimestamp(note)
    }

    suspend fun update(note: Note) {
        noteDao.updateWithTimestamp(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }
}