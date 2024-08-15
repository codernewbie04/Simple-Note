package com.akmalmf.simplenote.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.akmalmf.simplenote.data.model.Note

/**
 * Created by Akmal Muhamad Firdaus on 2024/08/15 17:14
 * akmalmf007@gmail.com
 */
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class SimpleNoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: SimpleNoteDatabase? = null

        fun getDatabase(context: Context): SimpleNoteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SimpleNoteDatabase::class.java,
                    "simple_note_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}