package com.example.u3exam.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_characters")
data class FavoriteCharacter(
    @PrimaryKey
    val id: Int, // Use the character ID from the API as the primary key
    val name: String,
    val species: String,
    val image: String
)