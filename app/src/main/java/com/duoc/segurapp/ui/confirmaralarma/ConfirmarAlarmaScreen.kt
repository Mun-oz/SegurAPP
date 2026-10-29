package com.duoc.segurapp.ui.confirmaralarma

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.duoc.segurapp.model.Alarma
import com.duoc.segurapp.viewmodel.ViewAlarma

@Composable
fun ConfirmarAlarmaScreen(
    viewAlarma: ViewAlarma,
    onVolverClick: () -> Unit
){
    var actualizarPantalla by remember { mutableStateOf(false) }
    val alarmas = viewAlarma.alarmas

    Scaffold(
        containerColor = Color(0xFFFFF7ED)
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxWidth().padding(innerPadding).padding(20.dp)
        ) {
            Text(
                text = "Confirmar Alerta",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF164E63)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Revisa las alertas pendientes y confirma"
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (alarmas.isEmpty()) {
                Text(
                    text = "No hay alarmas registradas",
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = alarmas,
                        key = { it.id }
                    ) { alarma ->
                        TarjetaAlarma(
                            alarma = alarma,
                            onConfirmarClick = {
                                viewAlarma.confirmarAlarma(alarma.id)
                                actualizarPantalla = !actualizarPantalla
                            })
                    }
                }
            }

            Button(onClick = onVolverClick,
                modifier = Modifier.fillMaxWidth().padding(top = 12.dp)) {
                Text("Volver al Menu")
            }

        }
    }
}

@Composable
private fun TarjetaAlarma(
    alarma: Alarma,
    onConfirmarClick: () -> Unit
){
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = alarma.descripcion,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(text = "Zona: ${alarma.zona}")

            Text(
                text = "Estado: ${alarma.estado}",
                color = if(alarma.estado == "Confirmada"){
                    Color(0xFF15803D)
                }else{
                    Color(0xFFB91C1C)
                }
            )

            if(alarma.estado == "Pendiente"){
                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onConfirmarClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Confirmar Alarma")
                }
            }
        }
    }
}