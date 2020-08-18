package com.melkov.librariansguide.screens.fragmentreader

import com.melkov.librariansguide.data.repository.LibraryRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ReaderListPresenter() : MvpPresenter<ReaderListView>() {

    private lateinit var libraryRepository: LibraryRepository

    constructor(libraryRepository: LibraryRepository) : this() {
        this.libraryRepository = libraryRepository
    }

    var disposeBag: CompositeDisposable = CompositeDisposable()

    fun loadReaders() {
        disposeBag.add(
            libraryRepository.getLibraryInfo()
                .subscribeOn(Schedulers.io())
                .map {
                    it.readers.sortedBy {
                        it.name.toLowerCase()
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        viewState.stopLoading()
                        viewState.setupReaders(it)
                    }, {
                        viewState.showError(it.toString())
                    }
                )
        )
    }

    fun searchReader(reader: String) {
        disposeBag.add(
            libraryRepository.getLibraryInfo().subscribeOn(Schedulers.io()).map {
                it.readers.sortedBy {
                    it.name.toLowerCase()
                }
                it.readers.filter {
                    it.name.toLowerCase().contains(reader.toLowerCase())
                }
            }
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                    {
                        viewState.stopLoading()
                        if (reader.isNotEmpty())
                            viewState.setupReaders(it)
                        else
                            loadReaders()
                    }, {
                        viewState.showError(it.localizedMessage)
                    }
                )
        )
    }
}