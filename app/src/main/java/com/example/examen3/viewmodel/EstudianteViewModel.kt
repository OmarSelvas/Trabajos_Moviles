package com.example.examen3.viewmodel

import androidx.lifecycle.ViewModel
import com.example.examen3.model.Estudiante
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.concurrent.atomic.AtomicInteger

class EstudianteViewModel : ViewModel() {

    private val idCounter = AtomicInteger(0)

    private val _estudiantes = MutableStateFlow<List<Estudiante>>(emptyList())

    val estudiantes: StateFlow<List<Estudiante>> = _estudiantes.asStateFlow()

    init {
        _estudiantes.value = listOf(
            Estudiante(idCounter.getAndIncrement(), "Juan", "Pérez", "5to", "A", 85.5),
            Estudiante(idCounter.getAndIncrement(), "Ana", "García", "5to", "A", 92.0),
            Estudiante(idCounter.getAndIncrement(), "Luis", "Martínez", "6to", "B", 78.0),
            Estudiante(idCounter.getAndIncrement(), "Maria", "Lopez", "6to", "B", 98.5),
            Estudiante(idCounter.getAndIncrement(), "Carlos", "Sánchez", "5to", "A", 65.0)
        )
    }


    fun agregarEstudiante(nombre: String, apellidos: String, grado: String, grupo: String, puntaje: Double) {
        val nuevoEstudiante = Estudiante(idCounter.getAndIncrement(), nombre, apellidos, grado, grupo, puntaje)
        // 'update' es una forma segura de actualizar el StateFlow
        _estudiantes.update { listaActual -> listaActual + nuevoEstudiante }
    }

    fun eliminarEstudiante(id: Int) {
        _estudiantes.update { listaActual ->
            listaActual.filterNot { it.id == id }
        }
    }

    fun editarEstudiante(id: Int, nombre: String, apellidos: String, grado: String, grupo: String, puntaje: Double) {
        _estudiantes.update { listaActual ->
            listaActual.map { estudianteExistente ->
                if (estudianteExistente.id == id) {
                    // Crea una copia del estudiante con los nuevos datos
                    estudianteExistente.copy(nombre = nombre, apellidos = apellidos, grado = grado, grupo = grupo, puntaje = puntaje)
                } else {
                    estudianteExistente // Deja los demás estudiantes como están
                }
            }
        }
    }


    fun calcularPromedioGeneral(): Double {
        return if (_estudiantes.value.isNotEmpty()) {
            _estudiantes.value.map { it.puntaje }.average()
        } else {
            0.0
        }
    }

    fun obtenerEstudianteConMayorRezago(): Estudiante? {
        // minByOrNull es una forma segura de encontrar el elemento con el valor mínimo.
        return _estudiantes.value.minByOrNull { it.puntaje }
    }

    fun obtenerTop3Estudiantes(): List<Estudiante> {
        // Ordena la lista de mayor a menor puntaje y toma los primeros 3.
        return _estudiantes.value.sortedByDescending { it.puntaje }.take(3)
    }
}
