package com.melkov.librariansguide.data.model

import com.google.gson.annotations.SerializedName

data class LibraryResponse(
    @SerializedName("Books")
    var books: List<Book>,
    @SerializedName("Readers")
    var readers: List<Reader>
)