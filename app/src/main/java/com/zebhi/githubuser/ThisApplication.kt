package com.zebhi.githubuser

import android.app.Application
import com.facebook.stetho.Stetho

class ThisApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}
