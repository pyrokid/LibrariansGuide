package com.melkov.librariansguide.data.repository

import com.melkov.librariansguide.data.model.LibraryResponse
import com.melkov.librariansguide.data.network.LibraryApi
import com.melkov.librariansguide.data.offline.book.BookDao
import com.melkov.librariansguide.data.offline.reader.ReaderDao
import io.reactivex.rxjava3.core.Single

class LibraryRepository(
    private val libraryApi: LibraryApi
) {

    fun getLibraryInfo(): Single<LibraryResponse> {
        return libraryApi.getLibraryInfo()
    }
}