package com.melkov.librariansguide.screens.fragmentbook

import com.melkov.librariansguide.data.repository.LibraryRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class BookListPresenter() : MvpPresenter<BookListView>() {

    private lateinit var libraryRepository: LibraryRepository

    constructor(libraryRepository: LibraryRepository) : this() {
        this.libraryRepository = libraryRepository
    }

    var disposeBag: CompositeDisposable = CompositeDisposable()

    fun loadBooks() {
        disposeBag.add(
            libraryRepository.getLibraryInfo()
                .subscribeOn(Schedulers.io())
                .map {
                    it.books.sortedBy {
                        it.author.toLowerCase()
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        viewState.stopLoading()
                        viewState.setupBooks(it)
                    }, {
                        viewState.showError(it.toString())
                    }
                )
        )
    }

    fun searchBook(book: String) {
        disposeBag.add(
            libraryRepository.getLibraryInfo().subscribeOn(Schedulers.io()).map {
                it.books.sortedBy {
                    it.author.toLowerCase()
                }
                it.books.filter {
                    it.name.toLowerCase().contains(book.toLowerCase())
                }
            }
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                    {
                        viewState.stopLoading()
                        if (book.isNotEmpty())
                            viewState.setupBooks(it)
                        else
                            loadBooks()
                    }, {
                        viewState.showError(it.localizedMessage)
                    }
                )
        )
    }
}