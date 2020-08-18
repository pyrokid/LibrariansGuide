package com.melkov.librariansguide.screens.activitymain

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.melkov.librariansguide.GuideApp
import com.melkov.librariansguide.R
import com.melkov.librariansguide.utils.SectionsPagerAdapter
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    lateinit var searchButton: ImageView
    lateinit var searchField: EditText
    lateinit var appLabel: TextView
    lateinit var progressBar: ProgressBar

    /**
     Made for further offline cache features
    fun checkInternetConnection(): Boolean {
        val conMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = conMgr.activeNetworkInfo
        return netInfo != null
    }
    **/
    override fun onCreate(savedInstanceState: Bundle?) {
        GuideApp.appComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.fragment_container)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        searchButton = findViewById(R.id.search_button)
        searchField = findViewById(R.id.search_field)
        appLabel = findViewById(R.id.app_label)
        progressBar = findViewById(R.id.progress_bar)


        viewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                setDefaultToolbar()
            }
        })

        searchButton.setOnClickListener {
            if (searchField.visibility == View.GONE) {
                searchField.text.clear()
                searchButton.setImageResource(R.drawable.ic_cancel)
                searchField.visibility = View.VISIBLE
                appLabel.visibility = View.GONE
            } else {
                setDefaultToolbar()
            }
        }
    }

    fun setDefaultToolbar() {
        searchButton.setImageResource(R.drawable.ic_search)
        searchField.text.clear()
        searchField.visibility = View.GONE
        appLabel.visibility = View.VISIBLE
    }
}