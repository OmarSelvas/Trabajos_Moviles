package com.example.examen3.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
// Importaciones necesarias para el ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
// Importa tus vistas existentes
import com.example.examen3.views.FormView
import com.example.examen3.views.HomeView
import com.example.examen3.views.OscView
// --- ¡NUEVAS IMPORTACIONES! ---
import com.example.examen3.views.TabsScreen
import com.example.examen3.viewmodel.EstudianteViewModel

@Composable
fun NavManager(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ){
        composable(
            "Home",
            enterTransition = { slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(300)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(300)) }
        ) {
            HomeView(navController)
        }
        composable(
            "Formulario",
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(300)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(300)) }
        ) {
            FormView(navController)
        }
        composable (
            "Tema",
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(300)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(300)) }
        ){
            OscView(navController)
        }

        composable(
            "GestionAlumnos",
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(300)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(300)) }
        ) {
            val viewModel: EstudianteViewModel = viewModel()
            TabsScreen(viewModel = viewModel, navController = navController)
        }
    }
}
