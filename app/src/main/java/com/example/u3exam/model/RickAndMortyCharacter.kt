package com.example.u3exam.model

// This class wraps the API response
data class RickAndMortyResponse(
    val results: List<RickAndMortyCharacter>
)

// This class represents a single character
data class RickAndMortyCharacter(
    val id: Int,
    val name: String,
    val species: String,
    val image: String
)