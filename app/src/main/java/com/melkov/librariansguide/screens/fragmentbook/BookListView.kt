package com.melkov.librariansguide.screens.fragmentbook

import com.melkov.librariansguide.data.model.Book
import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface BookListView : MvpView {
    @StateStrategyType(SkipStrategy::class)
    fun setupBooks(books: List<Book>)

    @StateStrategyType(SkipStrategy::class)
    fun stopLoading()

    @StateStrategyType(SkipStrategy::class)
    fun showError(localizedMessage: String?)
}