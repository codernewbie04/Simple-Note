package com.akmalmf.simplenote.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by Akmal Muhamad Firdaus on 2024/08/15 17:01
 * akmalmf007@gmail.com
 */
@Entity(tableName = "notes")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    @ColumnInfo(name = "created_at") var createdAt: Long,
): Parcelable