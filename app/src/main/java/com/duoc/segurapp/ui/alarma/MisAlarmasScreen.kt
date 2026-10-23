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
    onVolverClick: () -> Unit
){
    Scaffold(
        containerColor = Color(0xFFF0FDFA)
    ) { innerPadding -> Column(
        modifier = Modifier.fillMaxSize().padding(innerPadding).padding(20.dp)
    ) {
        Text("Mis Alarmas",
            style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
    }


    }
}