package com.example.juicetracker_interop

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.juicetracker_interop.ui.home.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(JuiceTrackerApplication().appContainer.juiceRepository)
        }
    }
}

fun CreationExtras.JuiceTrackerApplication(): JuiceTrackerApplication =
    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JuiceTrackerApplication