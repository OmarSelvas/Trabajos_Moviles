package com.example.examen3.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.examen3.viewmodel.EstudianteViewModel

@Composable
fun EstadisticasScreen(viewModel: EstudianteViewModel) {
    val estudiantes by viewModel.estudiantes.collectAsState()
    val promedioGeneral = viewModel.calcularPromedioGeneral()
    val estudianteRezagado = viewModel.obtenerEstudianteConMayorRezago()
    val top3Estudiantes = viewModel.obtenerTop3Estudiantes()

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text("Estadísticas Generales", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(bottom = 8.dp))
        }

        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Promedio General de Puntajes", style = MaterialTheme.typography.titleMedium)
                    Text(String.format("%.2f", promedioGeneral), style = MaterialTheme.typography.displaySmall)
                }
            }
        }

        item {
            estudianteRezagado?.let {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Estudiante con Mayor Rezago", style = MaterialTheme.typography.titleMedium)
                        Text("${it.nombre} ${it.apellidos}", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                        Text("Puntaje: ${it.puntaje}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }

        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Top 3 Mejores Estudiantes", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    if (top3Estudiantes.isEmpty()) {
                        Text("No hay suficientes estudiantes.", style = MaterialTheme.typography.bodyMedium)
                    } else {
                        top3Estudiantes.forEachIndexed { index, estudiante ->
                            Text(
                                "${index + 1}. ${estudiante.nombre} ${estudiante.apellidos} - Puntaje: ${estudiante.puntaje}",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }
    }
}
