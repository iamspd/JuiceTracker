package com.example.juicetracker_interop

import android.app.Application
import com.example.juicetracker_interop.di.AppContainer
import com.example.juicetracker_interop.di.AppContainerImpl

class JuiceTrackerApplication : Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainerImpl(this)
    }
}