package com.example.mynavegation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mynavegation.views.ConfigureView
import com.example.mynavegation.views.DetailsView
import com.example.mynavegation.views.HomeView


@Composable
fun NavManager(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ){
        composable("Home") {
            HomeView(navController)
        }
        composable("Details") {
            DetailsView(navController)
        }
        composable ("CONFIG"){
            ConfigureView(navController)
        }
    }
}