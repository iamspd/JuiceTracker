package com.example.juicetracker_interop.data.repository

import com.example.juicetracker_interop.data.model.Juice
import kotlinx.coroutines.flow.Flow

interface JuiceRepository {

    suspend fun insertJuice(juice: Juice)
    suspend fun updateJuice(juice: Juice)
    suspend fun deleteJuice(juice: Juice)

    fun getJuiceStream(): Flow<List<Juice>>
    fun getJuiceById(id: Long): Flow<Juice>
}