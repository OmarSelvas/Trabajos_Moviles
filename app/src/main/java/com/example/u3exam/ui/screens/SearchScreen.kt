package com.example.u3exam.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.u3exam.model.FavoriteCharacter
import com.example.u3exam.model.RickAndMortyCharacter
import com.example.u3exam.ui.navigation.AppScreens
import com.example.u3exam.viewmodel.CharacterViewModel

@Composable
fun SearchScreen(viewModel: CharacterViewModel, navController: NavController) {
    val characters by viewModel.characters.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    val filteredCharacters = characters.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Column {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search by name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        LazyColumn {
            items(filteredCharacters) { character ->
                CharacterItem(character = character, viewModel = viewModel, navController = navController, favorites = favorites)
            }
        }
    }
}

@Composable
fun CharacterItem(
    character: RickAndMortyCharacter,
    viewModel: CharacterViewModel,
    navController: NavController,
    favorites: List<FavoriteCharacter>
) {
    val isFavorite = favorites.any { it.id == character.id }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate(AppScreens.DetailScreen.createRoute(character.name)) }
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier.size(100.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(text = character.name)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = character.species)
            }
            IconButton(onClick = { 
                if (isFavorite) {
                    // To remove, we need to create a FavoriteCharacter object that matches the one in the database
                    val favoriteToRemove = favorites.find { it.id == character.id }
                    favoriteToRemove?.let { viewModel.removeFavorite(it) }
                } else {
                    viewModel.addFavorite(character)
                }
            }) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite"
                )
            }
        }
    }
}