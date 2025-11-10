package com.example.examen3.datadb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FormDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFormData(data: FormEntity)


    @Query("SELECT * FROM formulario_data WHERE id = 1")
    suspend fun getFormData(): FormEntity?
}