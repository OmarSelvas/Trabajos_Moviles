package com.example.examen3.datadb

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "formulario_data")
data class FormEntity(

    @PrimaryKey(autoGenerate = false)
    val id: Int = 1,
    val nombre: String,
    val correo: String,
    val telefono: String
)