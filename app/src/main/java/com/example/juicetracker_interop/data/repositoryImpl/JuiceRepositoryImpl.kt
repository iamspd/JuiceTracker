package com.example.juicetracker_interop.data.repositoryImpl

import com.example.juicetracker_interop.data.JuiceDao
import com.example.juicetracker_interop.data.model.Juice
import com.example.juicetracker_interop.data.repository.JuiceRepository
import kotlinx.coroutines.flow.Flow

class JuiceRepositoryImpl(
    private val juiceDao: JuiceDao
) : JuiceRepository {

    override suspend fun insertJuice(juice: Juice) {
        juiceDao.insert(juice)
    }

    override suspend fun updateJuice(juice: Juice) {
        juiceDao.update(juice)
    }

    override suspend fun deleteJuice(juice: Juice) {
        juiceDao.delete(juice)
    }

    override fun getJuiceStream(): Flow<List<Juice>> {
        return juiceDao.getAll()
    }

    override fun getJuiceById(id: Long): Flow<Juice> {
        return juiceDao.getJuiceById(id)
    }
}