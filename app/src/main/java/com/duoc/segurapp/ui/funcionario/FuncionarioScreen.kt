package com.duoc.segurapp.ui.funcionario


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.duoc.segurapp.model.Alarma

@Composable
fun FuncionarioScreen(
    usuario: String,
    alarmas: List<Alarma>,
    onConfirmarAlarmaClick: (String) -> Unit,
    onCerrarAlarmaClick: (String) -> Unit,
    onSalirClick: () -> Unit
) {
    var estadoSeleccionado by remember {
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
                    "Funcionario",
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
                "Bienvenido,  $usuario",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(Modifier.height(20.dp))

            if (estadoSeleccionado == null) {

                Text(
                    "Resumen de casos",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(Modifier.height(16.dp))

                ResumenCaso(
                    "Casos abiertos",
                    alarmas.count { it.estado == "Pendiente" }
                ) {
                    estadoSeleccionado = "Pendiente"
                }

                ResumenCaso(
                    "Casos en revisión",
                    alarmas.count { it.estado == "En revisión" }
                ) {
                    estadoSeleccionado = "En revisión"
                }

                ResumenCaso(
                    "Casos cerrados",
                    alarmas.count { it.estado == "Cerrada" }
                ) {
                    estadoSeleccionado = "Cerrada"
                }

            } else {

                val filtradas = alarmas.filter {
                    it.estado == estadoSeleccionado
                }

                Text(
                    "Estado: $estadoSeleccionado",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(Modifier.height(16.dp))

                if (filtradas.isEmpty()) {
                    Text("No hay alarmas en esta categoría")
                }

                filtradas.forEach { alarma ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                "Caso: ${alarma.id.take(8)}",
                                style = MaterialTheme.typography.titleMedium
                            )

                            Spacer(Modifier.height(8.dp))

                            Text("Descripción: ${alarma.descripcion}")
                            Text("Zona: ${alarma.zona}")
                            Text("Estado: ${alarma.estado}")

                            Spacer(Modifier.height(12.dp))

                            if (alarma.estado == "Pendiente") {
                                Button(
                                    onClick = {
                                        onConfirmarAlarmaClick(alarma.id)
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Confirmar e iniciar revisión")
                                }
                            }

                            if (alarma.estado == "En revisión") {
                                Button(
                                    onClick = {
                                        onCerrarAlarmaClick(alarma.id)
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Cerrar caso")
                                }
                            }
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))

                OutlinedButton(
                    onClick = {
                        estadoSeleccionado = null
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Volver a los casos")
                }
            }
        }
    }
}

@Composable
fun ResumenCaso(
    titulo: String,
    cantidad: Int,
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
                titulo,
                style = MaterialTheme.typography.titleMedium
            )

            Text("Cantidad: $cantidad")
            Text(
                "Toca para revisar",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
