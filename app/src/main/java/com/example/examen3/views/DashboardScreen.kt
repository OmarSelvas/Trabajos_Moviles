package com.example.examen3.views // <-- Nota el nombre del paquete

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.examen3.model.Estudiante
import com.example.examen3.viewmodel.EstudianteViewModel
import com.example.examen3.components.EstudianteDialog

@Composable
fun DashboardScreen(viewModel: EstudianteViewModel) {
    val estudiantes by viewModel.estudiantes.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var estudianteAEditar by remember { mutableStateOf<Estudiante?>(null) }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(estudiantes, key = { it.id }) { estudiante ->
                EstudianteItem(
                    estudiante = estudiante,
                    onEdit = {
                        estudianteAEditar = it
                        showDialog = true
                    },
                    onDelete = { viewModel.eliminarEstudiante(it.id) }
                )
            }
        }

        FloatingActionButton(
            onClick = {
                estudianteAEditar = null
                showDialog = true
            },
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Agregar Estudiante")
        }

        if (showDialog) {
            EstudianteDialog(
                estudiante = estudianteAEditar,
                onDismiss = { showDialog = false },
                onConfirm = { nombre, apellidos, grado, grupo, puntaje ->
                    if (estudianteAEditar == null) {
                        viewModel.agregarEstudiante(nombre, apellidos, grado, grupo, puntaje)
                    } else {
                        viewModel.editarEstudiante(estudianteAEditar!!.id, nombre, apellidos, grado, grupo, puntaje)
                    }
                    showDialog = false
                }
            )
        }
    }
}

@Composable
fun EstudianteItem(estudiante: Estudiante, onEdit: (Estudiante) -> Unit, onDelete: (Estudiante) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "${estudiante.nombre} ${estudiante.apellidos}", style = MaterialTheme.typography.titleMedium)
                Text(text = "Grado: ${estudiante.grado} - Grupo: ${estudiante.grupo}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Puntaje: ${estudiante.puntaje}", style = MaterialTheme.typography.bodyMedium)
            }
            IconButton(onClick = { onEdit(estudiante) }) { Icon(Icons.Default.Edit, "Editar") }
            IconButton(onClick = { onDelete(estudiante) }) { Icon(Icons.Default.Delete, "Eliminar") }
        }
    }
}
