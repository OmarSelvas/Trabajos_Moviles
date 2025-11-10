package com.example.examen3.views

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.examen3.components.MyTopAppBar
import com.example.examen3.datadb.AppDatabase
import com.example.examen3.datadb.FormEntity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormView(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dao = remember { AppDatabase.getDatabase(context).FormDao() }

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    val usuariosGuardados by dao.getAllFormData().collectAsState(initial = emptyList())


    Scaffold(
        topBar = {
            MyTopAppBar(
                title = "Registro y Lista",
                showBackButton = true,
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = { Text("Teléfono") },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        // Evita guardar si el nombre está vacío
                        if (nombre.isNotBlank()) {
                            scope.launch {
                                val entity = FormEntity(nombre = nombre, correo = correo, telefono = telefono)
                                dao.insertFormData(entity)
                                nombre = ""
                                correo = ""
                                telefono = ""
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Guardar Usuario")
                }
            }

            Divider(modifier = Modifier.padding(horizontal = 20.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .animateContentSize(animationSpec = tween(300)),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(usuariosGuardados, key = { it.id }) { usuario ->
                    UserCard(usuario = usuario)
                }
            }
        }
    }
}

@Composable
fun UserCard(usuario: FormEntity) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = usuario.nombre,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            // Solo muestra el correo y el teléfono si no están vacíos
            if (usuario.correo.isNotBlank()) {
                Text(
                    text = "Correo: ${usuario.correo}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            if (usuario.telefono.isNotBlank()) {
                Text(
                    text = "Teléfono: ${usuario.telefono}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
