package com.example.examen3.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.examen3.components.CustomFloatingActionButton
import com.example.examen3.components.CustomOutlinedButton
import com.example.examen3.components.NormalButton
import com.example.examen3.components.pressClickEffect


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("INICIO") }
            )
        },
        floatingActionButton = {
            CustomFloatingActionButton(
                modifier = Modifier.pressClickEffect()
            )
        }
    ) { innerPadding ->

        Content(innerPadding, navController)
    }
}

@Composable
fun Content(innerPaddingValues: PaddingValues, navController: NavController) {
    Column(
        modifier = Modifier
            .padding(innerPaddingValues)
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate("GestionAlumnos") }) {
            Text("Gestionar Alumnos")
        }
        NormalButton(
            title = "¿Deseas ir al formulario?",
            modifier = Modifier.pressClickEffect(),
            onClick = { navController.navigate("Formulario") }
        )
        Spacer(modifier = Modifier.height(24.dp))

        NormalButton(
            title = "Cambio de tema",
            modifier = Modifier.pressClickEffect(),
            onClick = { navController.navigate("Tema") }
        )
        Spacer(modifier = Modifier.height(24.dp))

        CustomOutlinedButton(
            modifier = Modifier.pressClickEffect()
        )
    }
}
