package com.melkov.librariansguide.screens.fragmentreader

import com.melkov.librariansguide.data.model.Reader
import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ReaderListView : MvpView {
    @StateStrategyType(SkipStrategy::class)
    fun setupReaders(readers: List<Reader>)

    @StateStrategyType(SkipStrategy::class)
    fun stopLoading()

    @StateStrategyType(SkipStrategy::class)
    fun showError(localizedMessage: String?)
}