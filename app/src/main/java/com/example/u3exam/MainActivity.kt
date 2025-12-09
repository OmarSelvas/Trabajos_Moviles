package com.example.u3exam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.u3exam.api.RetrofitInstance
import com.example.u3exam.repository.CharacterRepository
import com.example.u3exam.room.AppDatabase
import com.example.u3exam.ui.navigation.AppNavigation
import com.example.u3exam.ui.theme.U3ExamTheme
import com.example.u3exam.viewmodel.CharacterViewModel
import com.example.u3exam.viewmodel.CharacterViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.getDatabase(this)
        val repository = CharacterRepository(RetrofitInstance.api, database.characterDao())
        val viewModelFactory = CharacterViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(CharacterViewModel::class.java)

        setContent {
            U3ExamTheme {
                AppNavigation(viewModel)
            }
        }
    }
}