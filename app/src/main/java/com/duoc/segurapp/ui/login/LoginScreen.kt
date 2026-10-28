package com.duoc.segurapp.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onFuncionarioLogin: () -> Unit,
    onVolverClick: () -> Unit,
    onRegistrarseClick: () -> Unit
){
    var perfil by remember { mutableStateOf("Ciudadano") }
    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
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
                text = "SegurApp",
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFF164E63)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text("Selecciona tu tipo de usuario")

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        perfil = "Ciudadano"
                        usuario = ""
                        contrasena = ""
                        mensaje = ""
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (perfil == "Ciudadano")
                            Color(0xFF164E63) else Color.Gray
                    )
                ) {
                    Text("Ciudadano")
                }

                Button(
                    onClick = {
                        perfil = "Funcionario"
                        usuario = ""
                        contrasena = ""
                        mensaje = ""
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (perfil == "Funcionario")
                            Color(0xFF164E63) else Color.Gray
                    )
                ) {
                    Text("Funcionario")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Inicio de sesión",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = usuario,
                onValueChange = { usuario = it },
                label = {
                    Text(
                        if (perfil == "Funcionario")
                            "Correo institucional"
                        else
                            "Nombre de usuario"
                    )
                },
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

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (usuario.isBlank() || contrasena.isBlank()) {
                        mensaje = "Completa todos los campos"
                    } else if (perfil == "Funcionario") {
                        if (!usuario.contains("@")) {
                            mensaje = "Ingresa un correo válido"
                        } else {
                            mensaje = ""
                            onFuncionarioLogin()
                        }
                    } else {
                        mensaje = ""
                        onLoginSuccess()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Iniciar sesión")
            }

            if (mensaje.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = mensaje,
                    color = Color(0xFFD92828)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                contentAlignment = Alignment.Center
            ) {
                if (perfil == "Ciudadano") {
                    TextButton(onClick = onRegistrarseClick) {
                        Text("¿No tienes cuenta? Regístrate")
                    }
                }
            }
            TextButton(onClick = onVolverClick) {
                Text("Volver al inicio")
            }
        }
    }
}