package com.melkov.librariansguide

import android.app.Application
import android.util.Log
import com.melkov.librariansguide.di.AppComponent
import com.melkov.librariansguide.di.DaggerAppComponent
import io.reactivex.rxjava3.plugins.RxJavaPlugins

class GuideApp : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        RxJavaPlugins.setErrorHandler {
            Log.e("ErrorHandler: ", it.localizedMessage.toString())
        }

        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder().context(this).build()
    }
}