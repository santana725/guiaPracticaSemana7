package com.example.ejerciciosroom.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ExerciseResultEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun exerciseResultDao(): ExerciseResultDao
}