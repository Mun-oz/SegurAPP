package com.duoc.segurapp.ui.googlemap

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import android.location.Geocoder
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun GoogleMapsScreen() {

    val sanJoaquin = LatLng(-33.4915, -70.6300)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(sanJoaquin, 13f)
    }

    var direccion by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(35.dp)
    ) {


        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Direccion",
            style = MaterialTheme.typography.titleLarge
        )

        OutlinedTextField(
            value = direccion,
            onValueChange = { direccion = it },
            label = { Text("Buscar dirección") },
            placeholder = { Text("Ej: Avenida Departamental") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (direccion.isBlank()) {
                    mensaje = "Ingresa una dirección"
                } else {
                    scope.launch {
                        mensaje = "Buscando..."

                        val lugar = withContext(Dispatchers.IO) {
                            try {
                                val geocoder = Geocoder(context)
                                geocoder.getFromLocationName(
                                    "$direccion, San Joaquín, Santiago, Chile",
                                    1
                                )?.firstOrNull()
                            } catch (e: Exception) {
                                null
                            }
                        }

                        if (lugar != null) {
                            val ubicacion = LatLng(
                                lugar.latitude,
                                lugar.longitude
                            )

                            cameraPositionState.move(
                                CameraUpdateFactory.newLatLngZoom(
                                    ubicacion, 16f
                                )
                            )
                            mensaje = "Ubicación encontrada"
                        } else {
                            mensaje = "No se encontró la dirección"
                        }
                    }
                }
            }
        ) {
            Text("Buscar")
        }

        if (mensaje.isNotEmpty()) {
            Text(text = mensaje)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Mapa de San Joaquín",
            style = MaterialTheme.typography.titleLarge
        )

        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(
                zoomControlsEnabled = true
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                cameraPositionState.move(
                    CameraUpdateFactory.newLatLngZoom(sanJoaquin, 13f)
                )
            }
        ) {
            Text("Volver a San Joaquín")
        }
    }
}
