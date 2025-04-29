package com.example.juicetracker_interop.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.juicetracker_interop.data.model.Juice

@Database(entities = [Juice::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    // DAO Instances
    abstract fun juiceDao(): JuiceDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context,
                    klass = AppDatabase::class.java,
                    name = "app_database"
                )
                    .fallbackToDestructiveMigration(false)
                    .build()
                    .also { Instance = it }
            }
        }
    }
}