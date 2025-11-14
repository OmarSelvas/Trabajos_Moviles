package com.example.examen3.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.examen3.model.Estudiante

@Composable
fun EstudianteDialog(
    estudiante: Estudiante?,
    onDismiss: () -> Unit,
    onConfirm: (nombre: String, apellidos: String, grado: String, grupo: String, puntaje: Double) -> Unit
) {

    var nombre by remember { mutableStateOf(estudiante?.nombre ?: "") }
    var apellidos by remember { mutableStateOf(estudiante?.apellidos ?: "") }
    var grado by remember { mutableStateOf(estudiante?.grado ?: "") }
    var grupo by remember { mutableStateOf(estudiante?.grupo ?: "") }
    var puntaje by remember { mutableStateOf(estudiante?.puntaje?.toString() ?: "") }

    Dialog(onDismissRequest = onDismiss) {
        Card(modifier = Modifier.padding(16.dp)) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Text(
                    text = if (estudiante == null) "Agregar Estudiante" else "Editar Estudiante",
                    style = MaterialTheme.typography.titleLarge
                )

                OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
                OutlinedTextField(value = apellidos, onValueChange = { apellidos = it }, label = { Text("Apellidos") })
                OutlinedTextField(value = grado, onValueChange = { grado = it }, label = { Text("Grado") })
                OutlinedTextField(value = grupo, onValueChange = { grupo = it }, label = { Text("Grupo") })
                OutlinedTextField(
                    value = puntaje,
                    onValueChange = { puntaje = it },
                    label = { Text("Puntaje") },
                    // Especificamos que el teclado debe ser numérico para este campo.
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Row {
                    TextButton(onClick = onDismiss) {
                        Text("Cancelar")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        // Convertimos el puntaje a Double. Si el usuario escribe algo inválido, se usará 0.0.
                        val puntajeDouble = puntaje.toDoubleOrNull() ?: 0.0
                        // Llamamos a la función 'onConfirm' con todos los datos recolectados.
                        onConfirm(nombre, apellidos, grado, grupo, puntajeDouble)
                    }) {
                        Text("Confirmar")
                    }
                }
            }
        }
    }
}
