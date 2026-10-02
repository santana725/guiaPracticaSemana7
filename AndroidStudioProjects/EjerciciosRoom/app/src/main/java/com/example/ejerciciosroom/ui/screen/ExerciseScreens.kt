package com.example.ejerciciosroom.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ejerciciosroom.viewmodel.ExerciseHistoryViewModel

@Composable
fun ExerciseScreens(
    viewModel: ExerciseHistoryViewModel
) {

    var numberText by remember {
        mutableStateOf("")
    }

    var baseText by remember {
        mutableStateOf("")
    }

    var heightText by remember {
        mutableStateOf("")
    }

    var message by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Ejercicios",
            style = MaterialTheme.typography.headlineSmall
        )

        // -------------------------------
        // TABLA DE MULTIPLICAR
        // -------------------------------

        Text(
            text = "Tabla de multiplicar",
            style = MaterialTheme.typography.titleMedium
        )

        OutlinedTextField(
            value = numberText,
            onValueChange = {
                numberText = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Número")
            }
        )

        Button(
            onClick = {

                val number = numberText.toIntOrNull()

                if (number != null) {

                    for (i in 1..10) {

                        val tableResult = number * i

                        viewModel.saveResult(
                            exerciseType = "Tabla de multiplicar",
                            inputValue = "$number × $i",
                            result = tableResult.toString()
                        )
                    }

                    message = "Tabla guardada correctamente."

                } else {

                    message = "Ingresa un número válido."
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar tabla")
        }

        Spacer(
            modifier = Modifier.padding(4.dp)
        )

        // -------------------------------
        // ÁREA DE RECTÁNGULO
        // -------------------------------

        Text(
            text = "Área de rectángulo",
            style = MaterialTheme.typography.titleMedium
        )

        OutlinedTextField(
            value = baseText,
            onValueChange = {
                baseText = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Base")
            }
        )

        OutlinedTextField(
            value = heightText,
            onValueChange = {
                heightText = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Altura")
            }
        )

        Button(
            onClick = {

                val base = baseText.toDoubleOrNull()
                val height = heightText.toDoubleOrNull()

                if (base != null && height != null) {

                    val area = base * height

                    viewModel.saveResult(
                        exerciseType = "Área de rectángulo",
                        inputValue = "Base: $base, Altura: $height",
                        result = area.toString()
                    )

                    message = "Área guardada correctamente."

                } else {

                    message = "Ingresa valores válidos."
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular y guardar área")
        }

        if (message.isNotEmpty()) {

            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}