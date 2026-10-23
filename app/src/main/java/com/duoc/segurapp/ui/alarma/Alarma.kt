package com.duoc.segurapp.ui.alarma

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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


@Composable
fun registrarAlarma(
    onRegistrarAlarmaClick: (String, String) -> Unit,
    onVolverClick: () -> Unit
){
    var descripcion by remember{mutableStateOf("")}
    var zona by remember{mutableStateOf("")}

    Scaffold(    containerColor = Color(0xFFFFF7ED)){ innerPadding -> Column(
        modifier = Modifier.fillMaxSize().padding(innerPadding).padding(24.dp),
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Registrar Alarma",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = descripcion,
            onValueChange = {descripcion = it},
            label = {Text("Descripcion de la emergencia")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = zona,
            onValueChange = {zona = it},
            label = {Text("Zona o Direccion")},
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {onRegistrarAlarmaClick(descripcion,zona)},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar Alarma")
        }
        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onVolverClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al Menu")
        }
    }}
}