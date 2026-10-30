package com.duoc.segurapp.ui.registro

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.duoc.segurapp.viewmodel.UsuarioViewModel

@Composable
fun RegistroScreen(
    usuarioViewModel: UsuarioViewModel,
    onRegistrarClick: () -> Unit,
    onVolverClick: () -> Unit
) {
    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var confirmarContrasena by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color(0xFFFFF7ED)
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Registro de ciudadano",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = usuario,
                onValueChange = { usuario = it },
                label = { Text("Nombre de usuario") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = confirmarContrasena,
                onValueChange = { confirmarContrasena = it },
                label = { Text("Confirmar contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (usuario.isBlank() ||
                        contrasena.isBlank() ||
                        confirmarContrasena.isBlank()
                    ) {
                        mensaje = "Completa todos los campos"
                    } else if (contrasena.length < 6) {
                        mensaje = "La contraseña debe tener al menos 6 caracteres"
                    } else if (contrasena != confirmarContrasena) {
                        mensaje = "Las contraseñas no coinciden"
                    } else {
                        usuarioViewModel.registrarUsuario(
                            usuario.trim(),
                            contrasena
                        ) { registrado ->

                            if (registrado) {
                                mensaje = ""
                                onRegistrarClick()
                            } else {
                                mensaje = "El nombre de usuario ya está registrado"
                            }
                        }
                    }

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrarse")
            }

            if (mensaje.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = mensaje,
                    color = Color(0xFFD92828)
                )
            }

            TextButton(onClick = onVolverClick) {
                Text("Volver al inicio de sesión")
            }
        }
    }
}
