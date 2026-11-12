package com.duoc.segurapp.ui.ciudadano

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CiudadanoScreen(
    usuario: String,
    onCerrarSesionClick: () -> Unit,
    onSeguimientoClick: () -> Unit,
    onMisAlarmasClick: () -> Unit,
    onReportarEmergenciaClick: () -> Unit,
    onAbrirMapaClick: () -> Unit
) {

    val azul = Color(0xFF164E63)
    val rojo = Color(0xFFC92A2A)

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
                Column {
                    Text(
                        text = "Hola, $usuario",
                        style = MaterialTheme.typography.titleLarge,
                        color = azul
                    )

                    Text(
                        text = "Bienvenido a SegurApp",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }

                TextButton(onClick = onCerrarSesionClick) {
                    Text("Salir", color = azul)
                }
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "¿Qué necesitas hacer?",
                style = MaterialTheme.typography.titleLarge,
                color = azul,
                fontWeight = FontWeight.Bold
            )

            // Opción principal: reportar emergencia
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onReportarEmergenciaClick),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = rojo
                )
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("🚨", fontSize = 28.sp)

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Reportar emergencia",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Informa una situación de riesgo",
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }

                    Text(
                        text = "›",
                        color = Color.White,
                        fontSize = 28.sp
                    )
                }
            }

            // Accesos a reportes y seguimiento
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .height(140.dp)
                        .clickable(onClick = onMisAlarmasClick),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("📋", fontSize = 25.sp)

                        Text(
                            text = "Mis reportes",
                            color = azul,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Consulta tus avisos",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .height(140.dp)
                        .clickable(onClick = onSeguimientoClick),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("🔎", fontSize = 25.sp)

                        Text(
                            text = "Seguimiento",
                            color = azul,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Revisa su estado",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            }

            // Acceso al mapa
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onAbrirMapaClick),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Mapa de San Joaquín",
                        style = MaterialTheme.typography.titleMedium,
                        color = azul,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Busca calles y sectores de la comuna",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .background(
                                Color(0xFFE4E8E5),
                                RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("🗺️", fontSize = 32.sp)

                            Text(
                                text = "Abrir mapa interactivo",
                                color = azul,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }
}
