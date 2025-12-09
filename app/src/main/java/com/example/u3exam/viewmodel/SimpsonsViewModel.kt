package com.example.u3exam.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.u3exam.model.FavoriteCharacter
import com.example.u3exam.model.RickAndMortyCharacter
import com.example.u3exam.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterViewModel(private val repository: CharacterRepository) : ViewModel() {

    private val _characters = MutableStateFlow<List<RickAndMortyCharacter>>(emptyList())
    val characters: StateFlow<List<RickAndMortyCharacter>> = _characters.asStateFlow()

    private val _favorites = MutableStateFlow<List<FavoriteCharacter>>(emptyList())
    val favorites: StateFlow<List<FavoriteCharacter>> = _favorites.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        fetchCharacters()
        fetchFavorites()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _characters.value = repository.getCharacters()
            } catch (e: Exception) {
                Log.e("CharacterViewModel", "Error fetching characters", e)
            }
            _isLoading.value = false
        }
    }

    private fun fetchFavorites() {
        viewModelScope.launch {
            repository.getFavoriteCharacters().collect {
                _favorites.value = it
            }
        }
    }

    fun addFavorite(character: RickAndMortyCharacter) {
        viewModelScope.launch {
            val favoriteCharacter = FavoriteCharacter(
                id = character.id,
                name = character.name,
                species = character.species,
                image = character.image
            )
            repository.insertFavoriteCharacter(favoriteCharacter)
        }
    }

    fun removeFavorite(character: FavoriteCharacter) {
        viewModelScope.launch {
            repository.deleteFavoriteCharacter(character)
        }
    }
}