package com.melkov.librariansguide.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.melkov.librariansguide.R
import com.melkov.librariansguide.data.model.Reader
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_book.view.*
import kotlinx.android.synthetic.main.item_reader.view.*

class ReadersViewHolder(private val readers: List<Reader>) :
    RecyclerView.Adapter<ReadersViewHolder.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(reader: Reader) {
            itemView.reader_name.text = reader.name
            itemView.reader_birthday.text = reader.birthday
            reader.imageUrl?.let {
                Picasso.get().load(reader.imageUrl).centerCrop().resize(100, 100)
                    .error(R.drawable.ic_error_noimage).into(itemView.book_image)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reader, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        readers.getOrNull(position)?.let { reader ->
            holder.bind(reader)
        }
    }

    override fun getItemCount(): Int = readers.size
}