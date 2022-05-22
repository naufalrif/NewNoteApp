package com.example.newnoteapp.notes

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Notes (
    @PrimaryKey(autoGenerate = true)
    val id : Int?,
    @ColumnInfo(name = "title")
    var title : String,
    @ColumnInfo(name = "note")
    var note : String,
) : Parcelable