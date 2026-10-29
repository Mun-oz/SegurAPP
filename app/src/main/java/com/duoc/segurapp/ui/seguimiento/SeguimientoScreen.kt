package com.duoc.segurapp.ui.seguimiento

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.duoc.segurapp.model.Alarma

@Composable
fun SeguimientoScreen(
    alarmas: List<Alarma>,
    onVolverClick: () -> Unit
) {
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
                text = "Seguimiento de casos",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (alarmas.isEmpty()) {

                Text("Todavía no hay alarmas registradas.")

            } else {

                alarmas.forEach { alarma ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = "Caso: ${alarma.id.take(8)}",
                                style = MaterialTheme.typography.titleMedium
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text("Descripción: ${alarma.descripcion}")
                            Text("Zona: ${alarma.zona}")

                            Spacer(modifier = Modifier.height(8.dp))

                            val colorEstado = when (alarma.estado) {
                                "Pendiente" -> Color(0xFFB45309)
                                "En revisión" -> Color(0xFF2563EB)
                                "Cerrada" -> Color(0xFF15803D)
                                else -> Color.Gray
                            }

                            Text(
                                text = "Estado: ${alarma.estado}",
                                color = colorEstado,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            when (alarma.estado) {

                                "Pendiente" -> {
                                    Text(
                                        "Tu emergencia fue enviada y está esperando atención."
                                    )
                                }

                                "En revisión" -> {
                                    Text(
                                        "Un funcionario inició la revisión del caso."
                                    )
                                }

                                "Cerrada" -> {
                                    Text(
                                        "El funcionario finalizó la atención del caso."
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onVolverClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver")
            }
        }
    }
}
