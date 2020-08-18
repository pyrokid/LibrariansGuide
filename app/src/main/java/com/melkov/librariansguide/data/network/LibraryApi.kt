package com.melkov.librariansguide.data.network

import com.melkov.librariansguide.data.model.LibraryResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface LibraryApi {
    @GET("library.json")
    fun getLibraryInfo(): Single<LibraryResponse>
}