package com.example.ejerciciosroom.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import com.example.ejerciciosroom.data.local.ExerciseResultEntity
import com.example.ejerciciosroom.viewmodel.ExerciseHistoryViewModel

@Composable
fun HistoryScreen(
    viewModel: ExerciseHistoryViewModel
) {
    val history by viewModel.history.collectAsState(initial = emptyList())

    var showClearDialog by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Historial de ejercicios",
                style = MaterialTheme.typography.headlineSmall
            )

            TextButton(
                onClick = {
                    showClearDialog = true
                },
                enabled = history.isNotEmpty()
            ) {
                Text("Limpiar")
            }
        }

        Spacer(modifier = Modifier.padding(8.dp))

        if (history.isEmpty()) {

            Text(
                text = "No hay ejercicios registrados.",
                style = MaterialTheme.typography.bodyLarge
            )

        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(
                    items = history,
                    key = { it.id }
                ) { exercise ->

                    HistoryItem(
                        exercise = exercise,
                        onDelete = {
                            viewModel.deleteResult(exercise.id)
                        }
                    )
                }
            }
        }
    }

    if (showClearDialog) {

        AlertDialog(
            onDismissRequest = {
                showClearDialog = false
            },
            title = {
                Text("Limpiar historial")
            },
            text = {
                Text(
                    "¿Estás seguro de que deseas eliminar " +
                            "todo el historial de ejercicios?"
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.clearHistory()
                        showClearDialog = false
                    }
                ) {
                    Text("Eliminar todo")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showClearDialog = false
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
private fun HistoryItem(
    exercise: ExerciseResultEntity,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = exercise.exerciseType,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.padding(4.dp))

            Text(
                text = "Entrada: ${exercise.inputValue}"
            )

            Text(
                text = "Resultado: ${exercise.result}"
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp)
            )

            TextButton(
                onClick = onDelete
            ) {
                Text("Eliminar")
            }
        }
    }
}