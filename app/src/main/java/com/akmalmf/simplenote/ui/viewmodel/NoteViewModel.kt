package com.akmalmf.simplenote.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.akmalmf.simplenote.data.database.SimpleNoteDatabase
import com.akmalmf.simplenote.data.model.Note
import com.akmalmf.simplenote.data.repository.NoteRepository
import kotlinx.coroutines.launch

/**
 * Created by Akmal Muhamad Firdaus on 2024/08/15 17:15
 * akmalmf007@gmail.com
 */
class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NoteRepository
    val notes: LiveData<List<Note>>

    init {
        val noteDao = SimpleNoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        notes = repository.allNotes
    }

    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }

    fun update(note: Note) = viewModelScope.launch {
        repository.update(note)
    }

    fun delete(note: Note) = viewModelScope.launch {
        repository.delete(note)
    }
}