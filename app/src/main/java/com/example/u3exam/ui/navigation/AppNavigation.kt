package com.example.u3exam.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.u3exam.R
import com.example.u3exam.ui.screens.DetailScreen
import com.example.u3exam.ui.screens.FavoritesScreen
import com.example.u3exam.ui.screens.LoadingScreen
import com.example.u3exam.ui.screens.SearchScreen
import com.example.u3exam.viewmodel.CharacterViewModel

@Composable
fun AppNavigation(viewModel: CharacterViewModel) {
    val navController = rememberNavController()
    val isLoading by viewModel.isLoading.collectAsState()

    if (isLoading) {
        LoadingScreen()
    } else {
        MainScreen(navController = navController, viewModel = viewModel)
    }
}

@Composable
fun MainScreen(navController: NavHostController, viewModel: CharacterViewModel) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        NavigationGraph(navController, viewModel, paddingValues)
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        AppScreens.SearchScreen,
        AppScreens.FavoritesScreen
    )
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    val icon = when (screen) {
                        AppScreens.SearchScreen -> R.drawable.ic_search
                        AppScreens.FavoritesScreen -> R.drawable.ic_favorite
                        else -> R.drawable.ic_search // Default icon
                    }
                    Icon(painterResource(id = icon), contentDescription = null)
                },
                label = { Text(screen.route.split("_")[0].replaceFirstChar { it.uppercase() }) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController, viewModel: CharacterViewModel, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = AppScreens.SearchScreen.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(AppScreens.SearchScreen.route) {
            SearchScreen(viewModel = viewModel, navController = navController)
        }
        composable(AppScreens.FavoritesScreen.route) {
            FavoritesScreen(viewModel = viewModel, navController = navController)
        }
        composable(AppScreens.DetailScreen.route) { backStackEntry ->
            val characterName = backStackEntry.arguments?.getString("characterName")
            DetailScreen(viewModel = viewModel, characterName = characterName)
        }
    }
}