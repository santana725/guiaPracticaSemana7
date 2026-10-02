package com.example.ejerciciosroom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejerciciosroom.data.local.ExerciseResultEntity
import com.example.ejerciciosroom.data.repository.ExerciseResultRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ExerciseHistoryViewModel(
    private val repository: ExerciseResultRepository
) : ViewModel() {

    val history: Flow<List<ExerciseResultEntity>> =
        repository.observeAll()

    fun saveResult(
        exerciseType: String,
        inputValue: String,
        result: String
    ) {
        viewModelScope.launch {
            repository.insert(
                ExerciseResultEntity(
                    exerciseType = exerciseType,
                    inputValue = inputValue,
                    result = result
                )
            )
        }
    }

    fun deleteResult(id: Long) {
        viewModelScope.launch {
            repository.deleteById(id)
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}