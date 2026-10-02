package com.example.ejerciciosroom.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseResultDao {

    @Insert
    suspend fun insert(exerciseResult: ExerciseResultEntity)

    @Query("SELECT * FROM exercise_results ORDER BY createdAt DESC")
    fun observeAll(): Flow<List<ExerciseResultEntity>>

    @Query("DELETE FROM exercise_results WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("DELETE FROM exercise_results")
    suspend fun deleteAll()
}