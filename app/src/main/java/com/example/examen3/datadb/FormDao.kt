package com.example.examen3.datadb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow // 1. Asegúrate de importar Flow

@Dao
interface FormDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFormData(data: FormEntity)

    @Query("SELECT * FROM formulario_data ORDER BY id DESC")
    fun getAllFormData(): Flow<List<FormEntity>>


}
