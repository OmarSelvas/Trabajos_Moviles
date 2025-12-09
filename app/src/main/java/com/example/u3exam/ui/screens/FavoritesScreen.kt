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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.u3exam.model.FavoriteCharacter
import com.example.u3exam.ui.navigation.AppScreens
import com.example.u3exam.viewmodel.CharacterViewModel

@Composable
fun FavoritesScreen(viewModel: CharacterViewModel, navController: NavController) {
    val favorites by viewModel.favorites.collectAsState()

    LazyColumn {
        items(favorites) { character ->
            FavoriteCharacterItem(character = character, viewModel = viewModel, navController = navController)
        }
    }
}

@Composable
fun FavoriteCharacterItem(
    character: FavoriteCharacter,
    viewModel: CharacterViewModel,
    navController: NavController
) {
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
            IconButton(onClick = { viewModel.removeFavorite(character) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Remove from favorites"
                )
            }
        }
    }
}