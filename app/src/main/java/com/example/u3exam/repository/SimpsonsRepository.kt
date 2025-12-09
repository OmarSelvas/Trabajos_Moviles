package com.example.u3exam.repository

import com.example.u3exam.api.ApiService
import com.example.u3exam.model.FavoriteCharacter
import com.example.u3exam.model.RickAndMortyCharacter
import com.example.u3exam.room.CharacterDao
import kotlinx.coroutines.flow.Flow

class CharacterRepository(private val apiService: ApiService, private val characterDao: CharacterDao) {

    suspend fun getCharacters(): List<RickAndMortyCharacter> {
        return apiService.getCharacters().results
    }

    fun getFavoriteCharacters(): Flow<List<FavoriteCharacter>> {
        return characterDao.getFavoriteCharacters()
    }

    suspend fun insertFavoriteCharacter(character: FavoriteCharacter) {
        characterDao.insertFavoriteCharacter(character)
    }

    suspend fun deleteFavoriteCharacter(character: FavoriteCharacter) {
        characterDao.deleteFavoriteCharacter(character)
    }
}