package com.example.mynavegation.views


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mynavegation.components.CustomIconButton
import com.example.mynavegation.components.CustomOutlinedButton
import com.example.mynavegation.components.NormalButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsView(navController: NavController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text("Details del grupo B")},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) {
        DetailsContent(it, navController)
    }
}

@Composable
fun DetailsContent(innerPaddingValues: PaddingValues, navController: NavController){
    Column(
        modifier = Modifier
            .padding(innerPaddingValues)
    ) {
        NormalButton("¿Deseas entrar a la configuracion? puchame", onClick = {
            navController.navigate("Config")
        })
        NormalButton("no sirve")
        NormalButton("tampoco este :D"){
            println("Hola no se que esta haciendo esto")
        }
        CustomOutlinedButton()
        CustomIconButton()
    }
}
