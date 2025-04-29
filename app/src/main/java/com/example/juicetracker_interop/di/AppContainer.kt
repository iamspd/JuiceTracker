package com.example.juicetracker_interop.di

import com.example.juicetracker_interop.data.repository.JuiceRepository

interface AppContainer {
    val juiceRepository: JuiceRepository
}