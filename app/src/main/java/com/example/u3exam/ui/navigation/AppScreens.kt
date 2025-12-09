package com.example.u3exam.ui.navigation

sealed class AppScreens(val route: String) {
    object LoadingScreen : AppScreens("loading_screen")
    object SearchScreen : AppScreens("search_screen")
    object DetailScreen : AppScreens("detail_screen/{characterName}") {
        fun createRoute(characterName: String) = "detail_screen/$characterName"
    }
    object FavoritesScreen : AppScreens("favorites_screen")
}