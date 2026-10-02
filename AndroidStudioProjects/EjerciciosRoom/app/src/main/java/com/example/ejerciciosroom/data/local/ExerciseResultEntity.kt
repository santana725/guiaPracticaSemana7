package com.example.ejerciciosroom.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_results")
data class ExerciseResultEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val exerciseType: String,

    val inputValue: String,

    val result: String,

    val createdAt: Long = System.currentTimeMillis()
)