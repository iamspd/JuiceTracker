package com.example.juicetracker_interop.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.juicetracker_interop.data.model.Juice
import kotlinx.coroutines.flow.Flow

@Dao
interface JuiceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(juice: Juice)

    @Update
    suspend fun update(juice: Juice)

    @Delete
    suspend fun delete(juice: Juice)

    @Query("SELECT * FROM juice")
    fun getAll(): Flow<List<Juice>>

    @Query("SELECT * FROM juice WHERE id = :id")
    fun getJuiceById(id: Long): Flow<Juice>

}