package com.duoc.segurapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onEmergenciaClick: () -> Unit = {},
    onIniciarSesionClick: () -> Unit = {}
){
    Scaffold{innerPadding -> Column(
        modifier = Modifier.fillMaxSize().padding(innerPadding).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text ="Seguridad Municipal",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Card(modifier= Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors()){
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalAlignment  = Alignment.CenterHorizontally
            ){
                Text(
                    text = "¿En que podemos ayudarte?",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier= Modifier.height(24.dp))
                Button(
                    onClick = onEmergenciaClick,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(text = "EMERGENCIA")
                }
                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onIniciarSesionClick,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(text = "Inicar Sesion")
                }

            }

        }
    }}
}