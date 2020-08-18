package com.melkov.librariansguide.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.melkov.librariansguide.data.network.LibraryApi
import com.melkov.librariansguide.data.offline.book.BookDao
import com.melkov.librariansguide.data.offline.reader.ReaderDao
import com.melkov.librariansguide.data.repository.LibraryRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideLibraryRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl("https://rx-agent.ru/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideLibraryApi(retrofit: Retrofit): LibraryApi =
        retrofit.create(LibraryApi::class.java)

    @Provides
    @Singleton
    fun provideLibraryRepository(
        libraryApi: LibraryApi
    ): LibraryRepository =
        LibraryRepository(libraryApi)

}