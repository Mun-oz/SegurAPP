package com.duoc.segurapp.ui.ciudadano

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duoc.segurapp.ui.googlemap.GoogleMapsScreen
import androidx.compose.ui.graphics.Color

@Composable
fun CiudadanoScreen(onCerrarSesionClick: () -> Unit) {

    Scaffold(
        containerColor = Color(0xFFFFF7ED),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Bienvenido",
                    style = MaterialTheme.typography.titleLarge
                )

                TextButton(onClick = onCerrarSesionClick) {
                    Text("Salir")
                }
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            GoogleMapsScreen()
        }
    }
}