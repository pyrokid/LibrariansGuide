package com.melkov.librariansguide.utils

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.melkov.librariansguide.R
import com.melkov.librariansguide.screens.fragmentbook.BookListFragment
import com.melkov.librariansguide.screens.fragmentreader.ReaderListFragment

private val TAB_TITLES = arrayOf(
    R.string.books_tab_title,
    R.string.readers_tab_title
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> BookListFragment()
            1 -> ReaderListFragment()
            else -> BookListFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}