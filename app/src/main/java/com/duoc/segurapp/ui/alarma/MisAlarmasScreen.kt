package com.duoc.segurapp.ui.alarma

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duoc.segurapp.model.Alarma

@Composable
fun MisAlarmasScreen(
    alarmas : List<Alarma>,
    onVolverClick: () -> Unit,
    onVolverCuidadanoClick: () -> Unit
){
    Scaffold(
        containerColor = Color(0xFFF0FDFA)
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(20.dp)
        ) {
            Text(
                "Mis Alarmas",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Consulta las alarmas que estan registradas")

            Spacer(modifier = Modifier.height(10.dp))

            if (alarmas.isEmpty()) {
                Text(
                    text = "Todavia no existen alarmas registradas",
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = alarmas,
                        key = { alarma -> alarma.id }
                    ) { alarma ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation =
                                CardDefaults.cardElevation(defaultElevation = 3.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = alarma.descripcion,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = "Zona: ${alarma.zona}")
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Estado: ${alarma.estado}",
                                    color = Color(0xFFB45309)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onVolverClick,
                modifier = Modifier.fillMaxWidth()
            ){
                Text("Volver al menu")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onVolverCuidadanoClick,
                modifier = Modifier.fillMaxWidth()){
                Text("Volver a mi perfil")
            }

        }
    }
}