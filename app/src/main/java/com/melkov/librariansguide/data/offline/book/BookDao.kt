package com.melkov.librariansguide.data.offline.book

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.melkov.librariansguide.data.model.Book

@Dao
interface BookDao {

    @Query("SELECT * FROM bookLocal")
    fun getBooks(): List<Book>

    @Query("DELETE FROM bookLocal")
    fun deleteBooks()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBooks(books: List<Book>)
}