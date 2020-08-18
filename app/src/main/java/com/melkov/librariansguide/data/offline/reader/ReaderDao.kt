package com.melkov.librariansguide.data.offline.reader

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.melkov.librariansguide.data.model.Reader

@Dao
interface ReaderDao {

    @Query("SELECT * FROM readerLocal")
    fun getReaders(): List<Reader>

    @Query("DELETE FROM readerLocal")
    fun deleteReaders()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReaders(readers: List<Reader>)
}