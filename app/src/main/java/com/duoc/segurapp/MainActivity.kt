
package com.duoc.segurapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.duoc.segurapp.model.Alarma
import com.duoc.segurapp.ui.alarma.registrarAlarma
import com.duoc.segurapp.ui.home.HomeScreen
import com.duoc.segurapp.ui.login.LoginScreen
import com.duoc.segurapp.ui.theme.SegurAPPTheme
import com.duoc.segurapp.viewmodel.ViewAlarma

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SegurAPPTheme {

                var pantalla by remember {
                    mutableStateOf("Inicio")
                }

                val viewAlarma = remember {
                    ViewAlarma()
                }

                when (pantalla) {

                    "Inicio" -> {
                        HomeScreen(
                            onIniciarSesionClick = {
                                pantalla = "Login"
                            },
                            onEmergenciaClick = {
                                pantalla = "Registrar"
                            }
                        )
                    }

                    "Login" -> {
                        LoginScreen(
                            onLoginSuccess = {
                                pantalla = "Inicio"
                            },
                            onVolverClick = {
                                pantalla = "Inicio"
                            },
                            onRegistrarseClick = {
                                pantalla = "Inicio"
                            }
                        )

                    }

                    "Registrar" -> {
                        registrarAlarma(
                                onRegistrarAlarmaClick = { descripcion, zona ->
                                val alarma = Alarma(
                                    descripcion = descripcion,
                                    zona = zona
                                )

                                viewAlarma.registrarAlarma(alarma)
                                pantalla = "Inicio"
                            },
                            onVolverClick = {
                                pantalla = "Inicio"
                            }
                        )
                    }
                }
            }
        }
    }
}
