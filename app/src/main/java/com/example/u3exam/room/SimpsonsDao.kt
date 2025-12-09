package com.example.u3exam.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.u3exam.model.FavoriteCharacter
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Query("SELECT * FROM favorite_characters")
    fun getFavoriteCharacters(): Flow<List<FavoriteCharacter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteCharacter(character: FavoriteCharacter)

    @Delete
    suspend fun deleteFavoriteCharacter(character: FavoriteCharacter)
}