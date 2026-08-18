package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase

// exportSchema=true so future version bumps can ship a real Migration instead
// of relying on Room.fallbackToDestructiveMigration() in Graph.kt, which wipes
// every saved routine on upgrade. See volume-routine.md (maintenance notes) for the policy.
@Database(entities = [Routine::class], version = 2, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun routineDao(): RoutineDao
}
