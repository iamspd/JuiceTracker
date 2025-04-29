package com.example.juicetracker_interop.di

import android.content.Context
import com.example.juicetracker_interop.data.AppDatabase
import com.example.juicetracker_interop.data.repository.JuiceRepository
import com.example.juicetracker_interop.data.repositoryImpl.JuiceRepositoryImpl

class AppContainerImpl(
    private val context: Context
) : AppContainer {

    override val juiceRepository: JuiceRepository by lazy {
        JuiceRepositoryImpl(AppDatabase.getDatabase(context).juiceDao())
    }
}