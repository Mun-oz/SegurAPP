
package com.duoc.segurapp.ui.funcionario

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun FuncionarioScreen(onSalirClick: () -> Unit) {

    var categoriaSeleccionada by remember {
        mutableStateOf<String?>(null)
    }

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
                    text = "Funcionario",
                    style = MaterialTheme.typography.titleLarge
                )

                TextButton(onClick = onSalirClick) {
                    Text("Salir")
                }
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {

            Text(
                text = "Bienvenido, funcionario",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Resumen de casos",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))


            if (categoriaSeleccionada == null) {

                ResumenCaso("Casos abiertos") {
                    categoriaSeleccionada = "Casos abiertos"
                }

                ResumenCaso("Casos en revisión") {
                    categoriaSeleccionada = "Casos en revisión"
                }

                ResumenCaso("Casos cerrados") {
                    categoriaSeleccionada = "Casos cerrados"
                }

            } else {

                val caso = when (categoriaSeleccionada) {
                    "Casos abiertos" -> "AL-001"
                    "Casos en revisión" -> "AL-002"
                    else -> "AL-003"
                }

                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Caso: $caso")
                        Text("Estado: $categoriaSeleccionada")
                        Text("Sector: Zona 3")
                        Text("Reporte ficticio de prueba")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { categoriaSeleccionada = null }
                ) {
                    Text("Volver a los casos")
                }
            }


            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            ) {
                Text("Registrar caso")
            }

        }
    }
}


@Composable
fun ResumenCaso(
    titulo: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = titulo,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Toca para revisar",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

