package com.melkov.librariansguide.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.melkov.librariansguide.R
import com.melkov.librariansguide.data.model.Book
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_book.view.*

class BooksViewHolder(private val books: List<Book>) :
    RecyclerView.Adapter<BooksViewHolder.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(book: Book) {
            itemView.book_title.text = book.name
            itemView.book_author_and_year.text = """${book.author} ${book.year}"""
            book.imageUrl?.let {
                Picasso.get().load(book.imageUrl).centerCrop().resize(100, 100)
                    .error(R.drawable.ic_error_noimage).into(itemView.book_image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        books.getOrNull(position)?.let { book ->
            holder.bind(book)
        }

    }

    override fun getItemCount(): Int = books.size
}