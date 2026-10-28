package com.duoc.segurapp.ui.seguimiento

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SeguimientoScreen(onVolverClick: () -> Unit) {

    Scaffold(
        containerColor = Color(0xFFFFF7ED)
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {

            Text(
                text = "Seguimiento de caso",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Caso AL-001",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Situación: Sospechosa")
                    Text("Sector: Zona 3")
                    Text("Prioridad: Media")
                    Text("Estado actual: En revisión")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Estado del caso",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text("✓ Enviado")
            Spacer(modifier = Modifier.height(12.dp))

            Text("● En revisión", color = Color(0xFFB7791F))
            Spacer(modifier = Modifier.height(12.dp))

            Text("○ Cerrado (pendiente)")

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Observaciones",
                style = MaterialTheme.typography.titleMedium
            )

            Text("Caso de prueba en revisión.")

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onVolverClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver")
            }
        }
    }
}
