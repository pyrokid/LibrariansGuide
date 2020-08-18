package com.melkov.librariansguide.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "readerLocal")
data class Reader(
    @PrimaryKey
    @SerializedName("Name")
    val name: String,
    @SerializedName("Birthday")
    val birthday: String,
    // Little extension for next features
    val imageUrl: String?
)