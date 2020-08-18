package com.melkov.librariansguide.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "bookLocal")
data class Book(
    @PrimaryKey
    @SerializedName("Name")
    val name: String,
    @SerializedName("Author")
    val author: String,
    @SerializedName("Year")
    val year: String,
    // Little extension for next features
    val imageUrl: String?
)