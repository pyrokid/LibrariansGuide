package com.melkov.librariansguide.screens.fragmentbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding.widget.RxTextView
import com.melkov.librariansguide.GuideApp
import com.melkov.librariansguide.R
import com.melkov.librariansguide.data.model.Book
import com.melkov.librariansguide.data.repository.LibraryRepository
import com.melkov.librariansguide.screens.activitymain.MainActivity
import com.melkov.librariansguide.utils.BooksViewHolder
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class BookListFragment : MvpAppCompatFragment(), BookListView {

    @Inject
    lateinit var libraryRepository: LibraryRepository

    @ProvidePresenter
    fun provideBookListPresenter(): BookListPresenter {
        return BookListPresenter(libraryRepository = libraryRepository)
    }

    @InjectPresenter
    lateinit var presenter: BookListPresenter

    private lateinit var booksContainer: RecyclerView
    private lateinit var retryButton: Button
    private lateinit var retryLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        GuideApp.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_books, container, false)

        retryButton = v.findViewById(R.id.retry_button)
        retryLabel = v.findViewById(R.id.retry_alert)
        booksContainer = v.findViewById(R.id.books_container)

            presenter.loadBooks()

            RxTextView.textChanges((activity as MainActivity).searchField)
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribe {
                    presenter.searchBook(it.toString())
                }

        retryButton.setOnClickListener {
            presenter.loadBooks()
            retryButton.visibility = View.GONE
            retryLabel.visibility = View.GONE
        }

        return v
    }

    override fun setupBooks(books: List<Book>) {
        booksContainer.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = BooksViewHolder(books)
        }
    }

    override fun stopLoading() {
        (activity as MainActivity).progressBar.visibility = View.GONE
    }

    override fun showError(localizedMessage: String?) {
        stopLoading()
        retryButton.visibility = View.VISIBLE
        retryLabel.visibility = View.VISIBLE
    }

}