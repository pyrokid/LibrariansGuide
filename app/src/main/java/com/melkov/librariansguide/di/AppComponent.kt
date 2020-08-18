package com.melkov.librariansguide.di

import android.content.Context
import com.melkov.librariansguide.screens.activitymain.MainActivity
import com.melkov.librariansguide.screens.fragmentbook.BookListFragment
import com.melkov.librariansguide.screens.fragmentreader.ReaderListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, DatabaseModule::class])
@Singleton
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: BookListFragment)
    fun inject(fragment: ReaderListFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}