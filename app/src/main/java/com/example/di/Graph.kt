package com.example.di

import android.content.Context
import androidx.room.Room
import com.example.data.AppDatabase
import com.example.data.RoutineRepository

object Graph {
    lateinit var database: AppDatabase
        private set
        
    val repository by lazy {
        RoutineRepository(database.routineDao())
    }
    
    fun provide(context: Context) {
        database = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "routines.db"
        ).build()
    }
}
