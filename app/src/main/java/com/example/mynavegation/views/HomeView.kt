package com.example.mynavegation.views

import androidx.compose.runtime.Composable


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mynavegation.components.CustomFloatingActionButton
import com.example.mynavegation.components.CustomIconButton
import com.example.mynavegation.components.CustomOutlinedButton
import com.example.mynavegation.components.NormalButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Cuarto B")}
            )
        },
        floatingActionButton = {
            CustomFloatingActionButton()
        }
    ) {
        Content(it, navController)
    }
}

@Composable
fun Content(innerPaddingValues: PaddingValues, navController: NavController){
    Column(
        modifier = Modifier
            .padding(innerPaddingValues)
    ) {
        NormalButton("Click", onClick = {
            navController.navigate("Details")
        })
        NormalButton("dale click")
        NormalButton("no le des click"){
            println("Hola no se que esta haciendo esto")
        }
        CustomOutlinedButton()
        CustomIconButton()
    }
}