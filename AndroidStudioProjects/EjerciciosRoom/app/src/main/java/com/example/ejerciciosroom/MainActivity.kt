package com.example.ejerciciosroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ejerciciosroom.data.local.DatabaseProvider
import com.example.ejerciciosroom.data.repository.ExerciseResultRepository
import com.example.ejerciciosroom.ui.screen.ExerciseScreens
import com.example.ejerciciosroom.ui.screen.HistoryScreen
import com.example.ejerciciosroom.viewmodel.ExerciseHistoryViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: ExerciseHistoryViewModel by viewModels {

        object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(
                modelClass: Class<T>
            ): T {

                val database = DatabaseProvider.getDatabase(
                    applicationContext
                )

                val repository = ExerciseResultRepository(
                    database.exerciseResultDao()
                )

                @Suppress("UNCHECKED_CAST")
                return ExerciseHistoryViewModel(
                    repository
                ) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MaterialTheme {

                var selectedTab by remember {
                    mutableIntStateOf(0)
                }

                val tabs = listOf(
                    "Ejercicios",
                    "Historial"
                )

                Column {

                    TabRow(
                        selectedTabIndex = selectedTab
                    ) {

                        tabs.forEachIndexed { index, title ->

                            Tab(
                                selected = selectedTab == index,
                                onClick = {
                                    selectedTab = index
                                },
                                text = {
                                    Text(title)
                                }
                            )
                        }
                    }

                    when (selectedTab) {

                        0 -> {
                            ExerciseScreens(
                                viewModel = viewModel
                            )
                        }

                        1 -> {
                            HistoryScreen(
                                viewModel = viewModel
                            )
                        }
                    }
                }
            }
        }
    }
}