package com.melkov.librariansguide.data.offline.reader

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.melkov.librariansguide.data.model.Book
import com.melkov.librariansguide.data.model.Reader
import com.melkov.librariansguide.data.offline.book.BookDao

@Database(entities = [Reader::class], version = 1)
abstract class ReaderDatabase : RoomDatabase() {

    abstract fun readerDao(): ReaderDao

    companion object {

        fun getDatabase(context: Context): ReaderDatabase =
            Room.databaseBuilder(
                context,
                ReaderDatabase::class.java,
                "readerLocal"
            ).build()
    }
}