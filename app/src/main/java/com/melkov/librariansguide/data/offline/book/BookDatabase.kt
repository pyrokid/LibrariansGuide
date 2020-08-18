package com.melkov.librariansguide.data.offline.book

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.melkov.librariansguide.data.model.Book

@Database(entities = [Book::class], version = 1)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {

        fun getDatabase(context: Context): BookDatabase =
            Room.databaseBuilder(
                context,
                BookDatabase::class.java,
                "bookLocal"
            ).build()
    }
}