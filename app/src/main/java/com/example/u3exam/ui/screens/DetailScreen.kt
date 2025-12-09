package com.example.u3exam.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.u3exam.viewmodel.CharacterViewModel

@Composable
fun DetailScreen(viewModel: CharacterViewModel, characterName: String?) {
    val characters by viewModel.characters.collectAsState()
    // Find the character by name from the list
    val character = characters.find { it.name == characterName }

    if (character != null) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier.height(300.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = character.name)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Species: ${character.species}")
        }
    } else {
        // You could show a message if the character is not found
        Text("Character not found")
    }
}