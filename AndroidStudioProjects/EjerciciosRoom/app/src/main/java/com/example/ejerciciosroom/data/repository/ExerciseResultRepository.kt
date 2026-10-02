package com.example.ejerciciosroom.data.repository

import com.example.ejerciciosroom.data.local.ExerciseResultDao
import com.example.ejerciciosroom.data.local.ExerciseResultEntity
import kotlinx.coroutines.flow.Flow

class ExerciseResultRepository(
    private val dao: ExerciseResultDao
) {

    fun observeAll(): Flow<List<ExerciseResultEntity>> {
        return dao.observeAll()
    }

    suspend fun insert(exerciseResult: ExerciseResultEntity) {
        dao.insert(exerciseResult)
    }

    suspend fun deleteById(id: Long) {
        dao.deleteById(id)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}