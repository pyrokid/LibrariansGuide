package com.melkov.librariansguide.di

import android.content.Context
import com.melkov.librariansguide.data.offline.book.BookDao
import com.melkov.librariansguide.data.offline.book.BookDatabase
import com.melkov.librariansguide.data.offline.reader.ReaderDao
import com.melkov.librariansguide.data.offline.reader.ReaderDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    internal fun provideBookDatabase(context: Context): BookDatabase {
        return BookDatabase.getDatabase(context)
    }

    @Provides
    internal fun provideBookDao(bookDatabase: BookDatabase): BookDao {
        return bookDatabase.bookDao()
    }

    @Provides
    internal fun provideReaderDatabase(context: Context): ReaderDatabase {
        return ReaderDatabase.getDatabase(context)
    }

    @Provides
    internal fun provideReaderDao(readerDatabase: ReaderDatabase): ReaderDao {
        return readerDatabase.readerDao()
    }

}